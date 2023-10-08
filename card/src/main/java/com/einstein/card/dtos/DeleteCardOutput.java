package com.einstein.card.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DeleteCardOutput",
        description = "Delete card output"
)
public class DeleteCardOutput extends GenericCardOutput {

    public DeleteCardOutput(String statusCode, String message) {
        super(statusCode, message);
    }
}
