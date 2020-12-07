package org.exercise.userdetails;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.exercise.userdetails.domain.UserDetails;
import org.exercise.userdetails.repo.UserDetailsReposity;
import org.exercise.userdetails.service.UserDetailsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserdetailsServiceTests {
	@Autowired
	UserDetailsService service;
	
	@MockBean
	UserDetailsReposity repo;
	
	@Test
	@DisplayName("UserDetails by Id ")
	public void findById_success()  {
        UserDetails u1 = TestUtiils.basicUserDetails();

        doReturn(Optional.of(u1)).when(repo).findById(1);
        
        UserDetails details = service.findById(1);
        
        assertSame(u1, details, "Objects are not same");
        
        verify(repo, atLeastOnce()).findById(1);
	}
	
	@Test
	@DisplayName("update UserDetails")
	public void update_UserDetails_success()  {
        UserDetails u1 = TestUtiils.basicUserDetails();
		
		doReturn(true).when(repo).existsById(1);
        doReturn(u1).when(repo).save(u1);
        
        UserDetails details = service.update(u1, 1);
        
        assertSame(u1, details, "Objects are not same");
        
        verify(repo, atLeastOnce()).existsById(1);
        verify(repo, atLeastOnce()).save(u1);
	}
}
