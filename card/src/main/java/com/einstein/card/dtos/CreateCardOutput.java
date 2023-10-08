package com.einstein.card.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "CreateCardOutput",
        description = "Create card output"
)
public class CreateCardOutput extends GenericCardOutput {

    public CreateCardOutput(String statusCode, String message) {
        super(statusCode, message);
    }

}
