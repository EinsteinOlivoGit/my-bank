package com.einstein.card;

import com.einstein.card.dtos.BuildDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
		info = @Info(
				title = "Card API",
				description = "Card microservice Rest API",
                version = "1.0",
				contact = @Contact(
						name = "Einstein",
						url = "https://www.linkedin.com/in/einstein/",
						email = "einstein@gmail.com"
				),
				license = @License(
						name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
				)

		),
		externalDocs = @ExternalDocumentation(
				description = "Find more info here",
                url = "https://www.linkedin.com/in/einstein/"
		)
)
@SpringBootApplication
@EnableConfigurationProperties(value = {BuildDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardApplication.class, args);
	}

}
