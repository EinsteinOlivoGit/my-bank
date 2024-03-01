package com.einstein.loan.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(
        name = "DeleteLoanOutput",
        description = "Output for delete loan"
)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DeleteLoanOutput extends GenericLoanOutput {

    public DeleteLoanOutput(String statusCode, String message) {
        super(statusCode, message);
    }
}
