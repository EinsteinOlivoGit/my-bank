package com.einstein.card.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Output for Update Card"
)
public class UpdateCardOutput extends GenericCardOutput {

    public UpdateCardOutput(String statusCode, String message) {
        super(statusCode, message);
    }
}
