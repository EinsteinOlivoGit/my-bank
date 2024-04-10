package com.einstein.loan.controllers;

import com.einstein.loan.constants.LoanConstants;
import com.einstein.loan.dtos.*;
import com.einstein.loan.services.ILoanService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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

import java.util.concurrent.TimeoutException;

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
    public ResponseEntity<ConsultLoanOutput> consultLoan(@Valid @RequestParam
                                                             @Pattern(regexp = "(^$|[0-9]{9}$)", message = "Mobile number must be 9 digits")
                                                             String mobileNumber) {
        logger.debug("entering consultLoan");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(loanService.consultLoan(mobileNumber));
        } finally {
            logger.debug("Exiting consultLoan");
        }
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
    public ResponseEntity<ConsultCardAndLoanOutput> consultData(@Valid @RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{9}$)", message = "Mobile number must be 9 digits")
                                                         String mobileNumber) {
        logger.debug("entering consultLoan");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(loanService.consultCardAndLoan(mobileNumber));
        } finally {
            logger.debug("Exiting consultLoan");
        }
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
    public ResponseEntity<CreateLoanOutput> createLoan(@Valid @RequestBody CreateLoanInput dto) {
        logger.debug("Entering createLoan");
        try {
            loanService.createLoan(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CreateLoanOutput(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
        } finally {
            logger.debug("Exiting createLoan");
        }
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
    public ResponseEntity<UpdateLoanOutput> updateLoan(@Valid @RequestBody UpdateLoanInput dto) {
        logger.debug("Entering updateLoan");
        try {
            loanService.updateLoan(dto);
            return ResponseEntity.status(HttpStatus.OK).body(new UpdateLoanOutput(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        } finally {
            logger.debug("Exiting updateLoan");
        }
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
    public ResponseEntity<DeleteLoanOutput> deleteLoan(@Valid @RequestParam
                                                       @Pattern(regexp = "(^$|[0-9]{9}$)", message = "Mobile number must be 9 digits")
                                                       String mobileNumber) {
        logger.debug("entering deleteLoan");
        try {
            loanService.deleteLoan(mobileNumber);
            return ResponseEntity.status(HttpStatus.OK).body(new DeleteLoanOutput(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        } finally {
            logger.debug("Exiting deleteLoan");
        }
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
    @RateLimiter(name = "getBuild", fallbackMethod = "fallbackGetBuild")
    @RequestMapping(method = RequestMethod.GET, path = "/version")
    public ResponseEntity<BuildDto> getBuild() {
        logger.debug("Entering getBuild");
        try {
//        throw new RuntimeException();
            return ResponseEntity.status(HttpStatus.OK).body(buildDto);
        } finally {
            logger.debug("Exiting getBuild");
        }
    }

    public ResponseEntity<BuildDto> fallbackGetBuild( Throwable throwable) {
        logger.debug("Entering fallbackGetBuild");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new BuildDto("0.0.1", "Data rate del fallback"));
        } finally {
            logger.debug("Exiting fallbackGetBuild");
        }
    }

    @Operation(
            summary = "Consult info",
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
    @Retry(name = "getInfo", fallbackMethod = "fallbackGetInfo")
    @RequestMapping(method = RequestMethod.GET, path = "/info")
    public ResponseEntity<BuildDto> getInfo() throws TimeoutException {
        logger.debug("Entering getInfo");
        try {
//        throw new TimeoutException();
            return ResponseEntity.status(HttpStatus.OK).body(buildDto);
        } finally {
            logger.debug("Exiting getInfo");
        }
    }

    public ResponseEntity<BuildDto> fallbackGetInfo(Throwable throwable) {
        logger.debug("Entering fallbackGetInfo");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new BuildDto("0.0.1", "Data random del fallback"));
        } finally {
            logger.debug("Exiting fallbackGetInfo");
        }

    }

}
