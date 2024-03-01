package com.einstein.loan.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(
        name = "CreateLoanOutput",
        description = "Output for create loan"
)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateLoanOutput extends GenericLoanOutput {

    public CreateLoanOutput(String statusCode, String description) {
        super(statusCode, description);
    }
}
