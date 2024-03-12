package com.einstein.loan.controllers;

import com.einstein.loan.constants.LoanConstants;
import com.einstein.loan.dtos.*;
import com.einstein.loan.services.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@RestController
@RequestMapping(path = "/api/v1/loans", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class LoanController {

    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    private final ILoanService loanService;

    @Autowired
    private final BuildDto buildDto;

    @Operation(
            summary = "Consult Loan",
            description = "Consult Loan",
            tags = {"Loan"}
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
    public ResponseEntity<ConsultLoanOutput> consultLoan(@RequestHeader("myBank-correlation-id") String correlationId,
                                                         @Valid @RequestParam
                                                             @Pattern(regexp = "(^$|[0-9]{9}$)", message = "Mobile number must be 9 digits")
                                                             String mobileNumber) {
        logger.debug("myBank-correlation-id found: {} ", correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(loanService.consultLoan(mobileNumber));
    }

    @Operation(
            summary = "Consult all data",
            description = "Consult data",
            tags = {"data"}
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
    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public ResponseEntity<ConsultCardAndLoanOutput> consultData(@RequestHeader("myBank-correlation-id") String correlationId,
                                                                @Valid @RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{9}$)", message = "Mobile number must be 9 digits")
                                                         String mobileNumber) {
        logger.debug("myBank-correlation-id found: {} ", correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(loanService.consultCardAndLoan(correlationId, mobileNumber));
    }

    @Operation(
            summary = "Create Loan",
            description = "Create Loan",
            tags = {"Loan"}
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
    public ResponseEntity<CreateLoanOutput> createLoan(@RequestHeader("myBank-correlation-id") String correlationId,
                                                       @Valid @RequestBody CreateLoanInput dto) {
        logger.debug("myBank-correlation-id found: {} ", correlationId);
        loanService.createLoan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateLoanOutput(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Update Loan",
            description = "Update Loan",
            tags = {"Loan"}
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
    public ResponseEntity<UpdateLoanOutput> updateLoan(@RequestHeader("myBank-correlation-id") String correlationId,
                                                       @Valid @RequestBody UpdateLoanInput dto) {
        logger.debug("myBank-correlation-id found: {} ", correlationId);
        loanService.updateLoan(dto);
        return ResponseEntity.status(HttpStatus.OK).body(new UpdateLoanOutput(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
    }

    @Operation(
            summary = "Delete Loan",
            description = "Delete Loan",
            tags = {"Loan"}
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
    public ResponseEntity<DeleteLoanOutput> deleteLoan(@RequestHeader("myBank-correlation-id") String correlationId,
                                                       @Valid @RequestParam
                                                       @Pattern(regexp = "(^$|[0-9]{9}$)", message = "Mobile number must be 9 digits")
                                                       String mobileNumber) {
        logger.debug("myBank-correlation-id found: {} ", correlationId);
        loanService.deleteLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new DeleteLoanOutput(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
    }

    @Operation(
            summary = "Consult Version",
            description = "Consult Version",
            tags = {"Loan"}
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
    public ResponseEntity<BuildDto> getBuild(@RequestHeader("myBank-correlation-id") String correlationId) {
        logger.debug("myBank-correlation-id found: {} ", correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(buildDto);
    }

}
