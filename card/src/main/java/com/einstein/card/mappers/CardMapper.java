package com.einstein.card.mappers;

import com.einstein.card.dtos.ConsultCardOutput;
import com.einstein.card.dtos.CreateCardInput;
import com.einstein.card.dtos.UpdateCardInput;
import com.einstein.card.models.Card;

public class CardMapper {
    public static ConsultCardOutput toConsultCardOutput(Card card) {
        return ConsultCardOutput.builder()
                .mobileNumber(card.getMobileNumber())
                .cardNumber(card.getCardNumber())
                .cardType(card.getCardType())
                .totalLimit(card.getTotalLimit())
                .amountUsed(card.getAmountUsed())
                .availableAmount(card.getAvailableAmount())
                .build();
    }

    public static Card toCard(CreateCardInput dto) {
        return Card.builder()
                .mobileNumber(dto.getMobileNumber())
                .cardNumber(dto.getCardNumber())
                .cardType(dto.getCardType())
                .totalLimit(dto.getTotalLimit())
                .amountUsed(dto.getAmountUsed())
                .availableAmount(dto.getAvailableAmount())
                .build();
    }

    public static Card toCard(UpdateCardInput dto) {
        return Card.builder()
                .mobileNumber(dto.getMobileNumber())
                .cardNumber(dto.getCardNumber())
                .cardType(dto.getCardType())
                .totalLimit(dto.getTotalLimit())
                .amountUsed(dto.getAmountUsed())
                .availableAmount(dto.getAvailableAmount())
                .build();
    }


}
