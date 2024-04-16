package com.einstein.card.functions;

import com.einstein.card.services.ICardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class CardFunction {

    private static final Logger logger = LoggerFactory.getLogger(CardFunction.class);

    @Bean
    public Consumer<String> updateCommunicationSent(ICardService cardService) {
        return x -> {
            logger.info("Actualizando registro de base de datos para tener constancia de que el mensaje ha sido enviado");
            cardService.updateCommunicationSent(x);
        };
    }
}
