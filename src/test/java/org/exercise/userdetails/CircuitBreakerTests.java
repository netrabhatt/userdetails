package org.exercise.userdetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;

import org.exercise.userdetails.rest.UserDetailsController;
import org.exercise.userdetails.service.UserDetailsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CircuitBreakerTests {
	@MockBean
	UserDetailsService service;
	
	@Test
	@DisplayName("CircuitBreaker open after 5 calls, if 20% calls failed.")
	public void circuitBreaker_open_after_five_calls_results_20_percent_failure() {
		UserDetailsController controller = new UserDetailsController(service, TestUtiils.circuitBreaker());
		doThrow(new NoSuchElementException()).when(service).findById(2);

		for (int i = 0; i < 10; i++) {
			controller.fetch(2);
		}

		verify(service, times(5)).findById(2);
	}
	
	@Test
	@DisplayName("fallback method success")
	public void fallback_method() {
		UserDetailsController controller = new UserDetailsController(service, TestUtiils.circuitBreaker());
        doThrow(new NoSuchElementException()).when(service).findById(2);
        
        ResponseEntity<?> responseEntity = controller.fetch(2);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode() );
        
        verify(service, atLeastOnce()).findById(2);
	}
}