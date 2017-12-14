package com.projects.security.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projects.security.models.User;
import com.projects.security.repositories.UserRepository;


@Service
@Repository
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	//@PostConstruct
	@Transactional
	public void create(User user) {
		
	}
	
	@Transactional(readOnly=true)
	 public List<User> getAll() {
		return userRepository.findAll();
		
	}
	
	@Transactional
	public void delete(Long id) {
		userRepository.delete(id);
	}
	
	 public User findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }
	 
	 public User findUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
	 
	 public List<User> findByUsernameContaining(String searchString){
			return userRepository.findByUsernameContaining(searchString);
		}
}
