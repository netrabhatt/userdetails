package org.exercise.userdetails.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class Config {
	
	@Bean
	@Qualifier("circuitBreaker")
	public CircuitBreaker circuitBreaker() {
		var circuitBreakerConfig = 
				CircuitBreakerConfig.custom().failureRateThreshold(20).slidingWindowSize(5).build();

		var timeLimiterConfig = TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(3)).build();

		var factory = new Resilience4JCircuitBreakerFactory();
		factory.configureDefault(id -> new Resilience4JConfigBuilder(id).timeLimiterConfig(timeLimiterConfig)
																		.circuitBreakerConfig(circuitBreakerConfig)
																		.build());

		return factory.create("circuitBreaker");
	}
}