package com.projects.security.repositories;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.security.models.PasswordResetToken;


@Transactional
public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long>{
	
	public PasswordResetToken findByToken(String token);

}
