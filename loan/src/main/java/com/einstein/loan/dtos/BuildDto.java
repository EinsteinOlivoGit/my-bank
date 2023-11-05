package com.einstein.loan.dtos;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "build")
public record BuildDto(String version, String description) {
}
