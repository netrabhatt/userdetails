package org.exercise.userdetails;

import java.time.Duration;

import org.exercise.userdetails.domain.UserDetails;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

public class TestUtiils {
	public static UserDetails basicUserDetails() {
		UserDetails u1 = new UserDetails();
		u1.setEmpId(1);
		u1.setFirstName("Netra");
		u1.setLastName("Bhatt");
		return u1;
	}
	public static CircuitBreaker circuitBreaker() {
		var circuitBreakerConfig = CircuitBreakerConfig.custom().failureRateThreshold(20).slidingWindowSize(5).build();

		var timeLimiterConfig = TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(3)).build();

		var factory = new Resilience4JCircuitBreakerFactory();
		factory.configureDefault(id -> new Resilience4JConfigBuilder(id).timeLimiterConfig(timeLimiterConfig)
				.circuitBreakerConfig(circuitBreakerConfig).build());

		return factory.create("circuitBreaker");
	}
}
