package com.einstein.loan.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(
        name = "UpdateLoanOutput",
        description = "Output for update loan"
)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateLoanOutput extends GenericLoanOutput{

    public UpdateLoanOutput(String statusCode, String message) {
        super(statusCode, message);
    }
}
