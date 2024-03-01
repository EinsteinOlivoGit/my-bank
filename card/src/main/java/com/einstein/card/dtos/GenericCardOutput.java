package com.einstein.card.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class GenericCardOutput {

    @Schema(
            name = "statusCode",
            description = "Status code",
            example = "200"
    )
    private String statusCode;
    @Schema(
            name = "message",
            description = "Message",
            example = "Card processed successfully"
    )
    private String message;
}
