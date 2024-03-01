package com.einstein.loan.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponseDto",
        description = "Error response dto"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponseDto {
    @Schema(
            name = "apiPath",
            description = "Path of the api"
    )
    private String apiPath;
    @Schema(
            name = "errorCode",
            description = "Error code"
    )
    private HttpStatus errorCode;
    @Schema(
            name = "errorMessage",
            description = "Error message"
    )
    private String errorMessage;
    @Schema(
            name = "errorTime",
            description = "Error time"
    )
    private LocalDateTime errorTime;
}
