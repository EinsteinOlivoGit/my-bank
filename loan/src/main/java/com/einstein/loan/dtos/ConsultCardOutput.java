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
public class ConsultCardOutput {

    @Schema(
            name = "mobileNumber",
            description = "Mobile number",
            example = "123456789"
    )
    private String mobileNumber;
    @Schema(
            name = "cardNumber",
            description = "Card number",
            example = "123456789012"
    )
    private String cardNumber;
    @Schema(
            name = "cardType",
            description = "Card type",
            example = "VISA"
    )
    private String cardType;
    @Schema(
            name = "totalLimit",
            description = "Total limit of the card",
            example = "100000"
    )
    private Long totalLimit;
    @Schema(
            name = "amountUsed",
            description = "Amount used of the card",
            example = "1000"
    )
    private Long amountUsed;
    @Schema(
            name = "availableAmount",
            description = "Available amount of the card",
            example = "9000"
    )
    private Long availableAmount;

}
