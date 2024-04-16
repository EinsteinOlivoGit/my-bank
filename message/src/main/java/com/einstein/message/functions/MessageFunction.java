package com.einstein.message.functions;

import com.einstein.message.dtos.CardMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunction {

    private static final Logger logger = LoggerFactory.getLogger(MessageFunction.class);

    @Bean
    public Function<CardMsgDto, CardMsgDto> email() {
        return x -> {
            logger.info("Sending email: " + x);
            return x;
        };
    }

    @Bean
    public Function<CardMsgDto, String> sms() {
        return x -> {
            logger.info("Sending sms: " + x);
            return x.cardNumber();
        };
    }
}
