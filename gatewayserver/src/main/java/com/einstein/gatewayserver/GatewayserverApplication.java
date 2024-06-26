package com.einstein.gatewayserver;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
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
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.circuitBreaker(config -> config.setName("cardCircuitBreaker")
										.setFallbackUri("forward:/contactSupport")))
						.uri("lb://CARD")
				)
				.route(p -> p
						.path("/mybank/api/v1/cards/**")
						.filters(f -> f.rewritePath("/mybank/api/v1/cards/(?<segment>.*)","/api/v1/cards/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.circuitBreaker(config -> config.setName("cardCircuitBreaker")
										.setFallbackUri("forward:/contactSupport")))
						.uri("lb://CARD")
				)
				.route(p -> p
						.path("/mybank/api/v1/loans**")
						.filters(f -> f.rewritePath("/mybank/api/v1/loans(?<segment>.*)","/api/v1/loans${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
										.retry(configRetry -> configRetry.setRetries(3)
												.setMethods(HttpMethod.GET)
												.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))
//								.circuitBreaker(config -> config.setName("loanCircuitBreaker")
//										.setFallbackUri("forward:/contactSupport"))
										.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
												.setKeyResolver(userKeyResolver()))
						)
						.uri("lb://LOAN")
				)
				.route(p -> p
						.path("/mybank/api/v1/loans/**")
						.filters(f -> f.rewritePath("/mybank/api/v1/loans/(?<segment>.*)","/api/v1/loans/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
										.retry(configRetry -> configRetry.setRetries(3)
												.setMethods(HttpMethod.GET)
												.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))
//								.circuitBreaker(config -> config.setName("loanCircuitBreaker")
//										.setFallbackUri("forward:/contactSupport"))
										.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
												.setKeyResolver(userKeyResolver()))
						)
						.uri("lb://LOAN")
				).build();
	}

	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
	}

	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1, 10, 10);
	}

	@Bean
	KeyResolver userKeyResolver() {
		return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
				.defaultIfEmpty("anonymous");
	}

}
