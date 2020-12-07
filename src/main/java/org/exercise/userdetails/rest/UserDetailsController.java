package org.exercise.userdetails.rest;

import java.util.function.Supplier;

import org.exercise.userdetails.domain.UserDetails;
import org.exercise.userdetails.service.UserDetailsService;
import org.exercise.userdetails.utils.UserDetailsExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userdetails")
public class UserDetailsController {
	private final UserDetailsService service;
	private final CircuitBreaker circuitBreaker;

	public UserDetailsController(UserDetailsService service, 
								 @Qualifier("circuitBreaker") CircuitBreaker circuitBreaker) {
		this.circuitBreaker = circuitBreaker;
		this.service = service;
	}

	@GetMapping("/{empId}")
	public ResponseEntity<?> fetch(@PathVariable int empId) {
		return circuitBreaker.run( ()->ResponseEntity.ok(service.findById(empId)), this::fallbackMethod);
	}	

	@PutMapping("/{empId}")
	public ResponseEntity<?> update(@RequestBody UserDetails userDetails, @PathVariable int empId) {
		Supplier<ResponseEntity<?>> supplier = () -> {
					service.update(userDetails, empId);
					return ResponseEntity.noContent().build();
				};
				
		return circuitBreaker.run(supplier, this::fallbackMethod);
	}

	private ResponseEntity<?> fallbackMethod(Throwable throwable) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(throwable.getMessage());
	}

}