package com.einstein.card.services.Impl;

import com.einstein.card.dtos.CardMsgDto;
import com.einstein.card.dtos.ConsultCardOutput;
import com.einstein.card.dtos.CreateCardInput;
import com.einstein.card.dtos.UpdateCardInput;
import com.einstein.card.exceptions.CardAlreadyExistException;
import com.einstein.card.exceptions.ResourceNotFoundException;
import com.einstein.card.mappers.CardMapper;
import com.einstein.card.models.Card;
import com.einstein.card.repositories.CardRepository;
import com.einstein.card.services.ICardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService implements ICardService {

    private static final Logger logger = LoggerFactory.getLogger(CardService.class);

    private final CardRepository cardRepository;
    private final StreamBridge streamBridge;

    @Override
    public void createCard(CreateCardInput dto) {
        Optional<Card> optionalCard = cardRepository.findByMobileNumber(dto.getMobileNumber());
        if (optionalCard.isPresent()) {
            throw new CardAlreadyExistException("Card already exists");
        }
        Card card = CardMapper.toCard(dto);
        Card cardSaved = cardRepository.save(card);
        sendCommunication(cardSaved);
    }

    private void sendCommunication(Card card) {
        var cardMsgDto = new CardMsgDto(Long.parseLong(card.getCardNumber()), "Miguel", "miguel69@gmail.com", card.getMobileNumber());
        logger.info("Sending communication: {}", cardMsgDto);
        var result = streamBridge.send("sendCommunication-out-0", cardMsgDto);
        logger.info("Communication sent, response: {}", result);
    }

    @Override
    public ConsultCardOutput consultCard(String mobileNumber) {
        Card card  = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        return CardMapper.toConsultCardOutput(card);
    }

    @Override
    public boolean updateCard(UpdateCardInput dto) {
        Card card = cardRepository.findByCardNumber(dto.getCardNumber()).orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber", dto.getCardNumber()));
        CardMapper.updateCard(card, dto);
        cardRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber", mobileNumber));
        cardRepository.delete(card);
        return true;
    }

    @Override
    public boolean updateCommunicationSent(String cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber", cardNumber));
        card.setCommunicationSent(true);
        cardRepository.save(card);
        return true;
    }
}
