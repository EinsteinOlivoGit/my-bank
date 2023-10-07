package com.einstein.card.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class GenericCardOutput {

    private String statusCode;
    private String message;
}
