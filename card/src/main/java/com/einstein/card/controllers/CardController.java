package com.einstein.card.controllers;

import com.einstein.card.constants.CardConstants;
import com.einstein.card.dtos.ConsultCardOutput;
import com.einstein.card.dtos.CreateCardInput;
import com.einstein.card.dtos.CreateCardOutput;
import com.einstein.card.services.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/cards", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class CardController {

    private final ICardService cardService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ConsultCardOutput> consultCard(@RequestParam String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(cardService.consultCard(mobileNumber));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CreateCardOutput> createCard(@RequestBody CreateCardInput dto) {
        cardService.createCard(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateCardOutput(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }


}
