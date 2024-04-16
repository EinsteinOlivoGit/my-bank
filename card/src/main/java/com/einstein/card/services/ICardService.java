package com.einstein.card.services;

import com.einstein.card.dtos.ConsultCardOutput;
import com.einstein.card.dtos.CreateCardInput;
import com.einstein.card.dtos.UpdateCardInput;

public interface ICardService {

    /**
     *
     * @param dto
     */
    void createCard(CreateCardInput dto);

    /**
     * @param mobileNumber
     * @return The DTO of the ConsultCard
     */
    ConsultCardOutput consultCard(String mobileNumber);

    /**
     * @param dto
     * @return A boolean to specifier the result
     */
    boolean updateCard(UpdateCardInput dto);

    /**
     * @param mobileNumber
     * @return A boolean to specifier the result
     */
    boolean deleteCard(String mobileNumber);

    /**
     * @param cardNumber
     * @return A boolenan to specifier the result
     */
    boolean updateCommunicationSent(String cardNumber);
}
