package com.einstein.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator apiRouteConfig(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/mybank/api/v1/cards**")
						.filters(f -> f.rewritePath("/mybank/api/v1/cards(?<segment>.*)","/api/v1/cards${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CARD")
				)
				.route(p -> p
						.path("/mybank/api/v1/cards/**")
						.filters(f -> f.rewritePath("/mybank/api/v1/cards/(?<segment>.*)","/api/v1/cards/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CARD")
				)
				.route(p -> p
						.path("/mybank/api/v1/loans**")
						.filters(f -> f.rewritePath("/mybank/api/v1/loans(?<segment>.*)","/api/v1/loans${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://LOAN")
				)
				.route(p -> p
						.path("/mybank/api/v1/loans/**")
						.filters(f -> f.rewritePath("/mybank/api/v1/loans/(?<segment>.*)","/api/v1/loans/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://LOAN")
				).build();
	}

}
