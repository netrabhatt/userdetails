package org.exercise.userdetails.utils;

import java.util.NoSuchElementException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class UserDetailsExceptionHandler {

	@ExceptionHandler( NoSuchElementException.class )
	protected ResponseEntity<Object> badInput(NoSuchElementException e) {
		return ResponseEntity.notFound().build();
    }
	
	@ExceptionHandler( {MethodArgumentTypeMismatchException.class, IllegalArgumentException.class, ConstraintViolationException.class })
    protected ResponseEntity<Object> handleConflict( Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
	
	@ExceptionHandler( RuntimeException.class )
	protected ResponseEntity<?> generalException(Exception e) { 
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
}