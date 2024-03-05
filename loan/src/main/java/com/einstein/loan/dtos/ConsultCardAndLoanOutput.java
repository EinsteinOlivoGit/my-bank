package com.einstein.loan.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(
        name = "ConsultCardOutput",
        description = "Consult card output"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ConsultCardAndLoanOutput {

    @Schema(
            name = "card",
            description = "All data of the card"
    )
    private ConsultCardOutput card;
    @Schema(
            name = "loan",
            description = "All data of the loan"
    )
    private ConsultLoanOutput loan;

}
