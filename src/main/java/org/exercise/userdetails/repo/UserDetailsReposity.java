package org.exercise.userdetails.repo;

import org.exercise.userdetails.domain.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsReposity extends CrudRepository<UserDetails, Integer> { 
}
