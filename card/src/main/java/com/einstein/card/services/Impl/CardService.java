package com.einstein.card.services.Impl;

import com.einstein.card.dtos.ConsultCardOutput;
import com.einstein.card.dtos.CreateCardInput;
import com.einstein.card.dtos.UpdateCardInput;
import com.einstein.card.exceptions.ResourceNotFoundException;
import com.einstein.card.mappers.CardMapper;
import com.einstein.card.models.Card;
import com.einstein.card.repositories.CardRepository;
import com.einstein.card.services.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService implements ICardService {

    private final CardRepository cardRepository;

    @Override
    public void createCard(CreateCardInput dto) {
        Card card = CardMapper.toCard(dto);
        cardRepository.save(card);
    }

    @Override
    public ConsultCardOutput consultCard(String mobileNumber) {
        Card card  = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        return CardMapper.toConsultCardOutput(card);
    }

    @Override
    public boolean updateCard(UpdateCardInput dto) {
        Card card = cardRepository.findByCardNumber(dto.getCardNumber()).orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber", dto.getCardNumber()));
        cardRepository.save(CardMapper.toCard(dto));
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber", mobileNumber));
        cardRepository.delete(card);
        return true;
    }
}
