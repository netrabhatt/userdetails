package org.exercise.userdetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.exercise.userdetails.domain.UserDetails;
import org.exercise.userdetails.rest.UserDetailsController;
import org.exercise.userdetails.service.UserDetailsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class UserDetailsControllerTest {
	@Autowired
	UserDetailsController controller;

	@MockBean
	UserDetailsService service;
	
	@Test
	@DisplayName("UserDetails by id success")
	public void userdetails_by_id() {
        UserDetails u1 = TestUtiils.basicUserDetails();
		doReturn(u1).when(service).findById(1);
        
        ResponseEntity<?> responseEntity = controller.fetch(1);
        Object body = responseEntity.getBody();
        
        assertSame(u1, body, "Objects are not same");
		verify(service, atLeastOnce()).findById(1);
	}
	
	@Test
	@DisplayName("UserDetails update success")
	public void userdetails_update() {
        UserDetails u1 = TestUtiils.basicUserDetails();
		
        doReturn(u1).when(service).update(u1, 1);
        
        ResponseEntity<?> responseEntity = controller.update(u1, 1);
        
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		verify(service, atLeastOnce()).update(u1, 1);
	}
}