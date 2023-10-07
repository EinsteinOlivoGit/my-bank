package com.einstein.card.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponseDto {
    private String apiPath;
    private String errorCode;
    private String errorMessage;
    private String errorTime;
}
