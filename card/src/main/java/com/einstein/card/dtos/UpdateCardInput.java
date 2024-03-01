package com.einstein.card.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Schema(
        name = "UpdateCardInput",
        description = "Update card output"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdateCardInput {

    @Schema(
            name = "mobileNumber",
            description = "Mobile number",
            example = "123456789"
    )
    @NotEmpty(message = "Mobile number must not be empty")
    @Pattern(regexp = "^$|[0-9]{9}$", message = "Mobile number must be 9 digits")
    private String mobileNumber;
    @Schema(
            name = "cardNumber",
            description = "Card number",
            example = "123456789012"
    )
    @NotEmpty(message = "Card number must not be empty")
    @Pattern(regexp = "^$|[0-9]{12}$", message = "Card number must be 12 digits")
    private String cardNumber;
    @Schema(
            name = "cardType",
            description = "Card type",
            example = "VISA"
    )
    @NotEmpty(message = "Card type must not be empty")
    private String cardType;
    @Schema(
            name = "totalLimit",
            description = "Total limit of the card",
            example = "100000"
    )

    @Positive(message = "Amount must be positive")
    private Long totalLimit;
    @Schema(
            name = "amountUsed",
            description = "Amount used of the card",
            example = "1000"
    )
    @PositiveOrZero(message = "Amount used must be positive or zero")
    private Long amountUsed;
    @Schema(
            name = "availableAmount",
            description = "Available amount of the card",
            example = "9000"
    )
    @PositiveOrZero(message = "Available amount must be positive or zero")
    private Long availableAmount;

}
