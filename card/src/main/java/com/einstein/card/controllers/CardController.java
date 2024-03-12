package com.einstein.card.controllers;

import com.einstein.card.constants.CardConstants;
import com.einstein.card.dtos.*;
import com.einstein.card.services.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Card",
        description = "Card API"
)
@RestController
@RequestMapping(path = "/api/v1/cards", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class CardController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @Autowired
    private final BuildDto buildDto;
    private final ICardService cardService;

    @Operation(
            summary = "Consult Card",
            description = "Consult Card",
            tags = {"Card"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ConsultCardOutput> consultCard(@RequestHeader("myBank-correlation-id") String correlationId,
                                                            @Valid @RequestParam
                                                             @Pattern(regexp = "(^$|[0-9]{9}$)", message = "Mobile number must be 9 digits")
                                                             String mobileNumber) {
        logger.debug("myBank-correlation-id found: {} ", correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(cardService.consultCard(mobileNumber));
    }

    @Operation(
            summary = "Create Card",
            description = "Create Card",
            tags = {"Card"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CreateCardOutput> createCard(@RequestHeader("myBank-correlation-id") String correlationId,
            @Valid @RequestBody CreateCardInput dto) {
        logger.debug("myBank-correlation-id found: {} ", correlationId);
        cardService.createCard(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateCardOutput(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Update Card",
            description = "Update Card",
            tags = {"Card"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<UpdateCardOutput> updateCard(@RequestHeader("myBank-correlation-id") String correlationId,
            @Valid @RequestBody UpdateCardInput dto) {
        logger.debug("myBank-correlation-id found: {} ", correlationId);
        cardService.updateCard(dto);
        return ResponseEntity.status(HttpStatus.OK).body(new UpdateCardOutput(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
    }

    @Operation(
            summary = "Delete Card",
            description = "Delete Card",
            tags = {"Card"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<DeleteCardOutput> deleteCard(@RequestHeader("myBank-correlation-id") String correlationId,
                                                           @Valid @RequestParam
                                                           @Pattern(regexp = "(^$|[0-9]{9}$)", message = "Mobile number must be 9 digits")
                                                           String mobileNumber) {
        logger.debug("myBank-correlation-id found: {} ", correlationId);
        cardService.deleteCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new DeleteCardOutput(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
    }

    @Operation(
            summary = "Consult Build Version",
            description = "Consult Build Version",
            tags = {"Card"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @RequestMapping(method = RequestMethod.GET, path = "/version")
    public ResponseEntity<BuildDto> getBuildVersion(@RequestHeader("myBank-correlation-id") String correlationId) {
        logger.debug("myBank-correlation-id found: {} ", correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(buildDto);
    }
}
