package com.einstein.card.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdateCardInput {

    @NotEmpty(message = "Mobile number must not be empty")
    @Pattern(regexp = "^$|[0-9]{9}$", message = "Mobile number must be 9 digits")
    private String mobileNumber;
    @NotEmpty(message = "Card number must not be empty")
    @Pattern(regexp = "^$|[0-9]{12}$", message = "Card number must be 12 digits")
    private String cardNumber;
    @NotEmpty(message = "Card type must not be empty")
    private String cardType;
    @Positive(message = "Amount must be positive")
    private Long totalLimit;
    @PositiveOrZero(message = "Amount used must be positive or zero")
    private Long amountUsed;
    @PositiveOrZero(message = "Available amount must be positive or zero")
    private Long availableAmount;

}
