package com.einstein.loan.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class GenericLoanOutput {

    @Schema(
            name = "statusCode",
            description = "Status code",
            example = "200"
    )
    private String statusCode;
    @Schema(
            name = "message",
            description = "Message",
            example = "Loan processed successfully"
    )
    private String message;
}
