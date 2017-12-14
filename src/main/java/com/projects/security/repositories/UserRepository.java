package com.projects.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.projects.security.models.User;


@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByUsername(String username);
	
	public User findByEmail(String email);
	
	List<User> findByUsernameContaining(String searchString);

}
