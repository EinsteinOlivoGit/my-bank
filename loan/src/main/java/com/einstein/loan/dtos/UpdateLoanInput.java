package com.einstein.loan.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(
        name = "UpdateLoanInput",
        description = "Input for update loan"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdateLoanInput {

    @Schema(
            name = "mobileNumber",
            description = "Mobile number",
            example = "123456789"
    )
    private String mobileNumber;
    @Schema(
            description = "Loan Number of the customer",
            example = "548732457654"
    )
    private String loanNumber;
    @Schema(
            description = "Type of the loan",
            example = "Home Loan"
    )
    private String loanType;
    @Schema(
            description = "Total loan amount",
            example = "100000"
    )
    private Long totalLoan;
    @Schema(
            description = "Total loan amount paid",
            example = "1000"
    )
    private Long amountPaid;
    @Schema(
            description = "Total outstanding amount against a loan",
            example = "99000"
    )
    private Long outstandingAmount;
}
