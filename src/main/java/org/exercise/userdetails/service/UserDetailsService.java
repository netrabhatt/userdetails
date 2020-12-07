package org.exercise.userdetails.service;

import java.util.NoSuchElementException;

import org.exercise.userdetails.domain.UserDetails;
import org.exercise.userdetails.repo.UserDetailsReposity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsService {
	private final UserDetailsReposity repo;

	public UserDetailsService(UserDetailsReposity repo) {
		this.repo = repo;
	}

	@Transactional
	public UserDetails update(UserDetails userDetails, int empId) {
		requireValidUserDetails(userDetails, empId);
		return repo.save(userDetails);
	}

	private void requireValidUserDetails(UserDetails userDetails, int empId) {
		if(userDetails.getEmpId() != empId) {
			throw new NoSuchElementException("Resource and submitted data do not refer to same resource.");
		}
		
		if(!repo.existsById(empId))
			throw new NoSuchElementException("Resource not found!");
	}

	@Transactional
	public UserDetails findById(int id) {
		return repo.findById(id).orElseThrow(()->new NoSuchElementException("Resource not found!"));
	}
}