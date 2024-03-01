package com.einstein.loan.dtos;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "build")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuildDto {
    private String version;
    private String description;
}
