package com.projects.security.services;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.projects.security.models.PasswordResetToken;
import com.projects.security.models.User;
import com.projects.security.repositories.PasswordTokenRepository;


@Service
@Repository
public class PasswordService implements MessageSourceAware{
	
	@Autowired
	private PasswordTokenRepository passwordTokenRepository;
	
	@Inject
	private Environment environment;
	
	private MessageSource messageSource;
	
	public void createPasswordResetTokenForUser(User user, String token) {
	    //PasswordResetToken myToken = new PasswordResetToken(token, user);
		PasswordResetToken myToken = new PasswordResetToken();
	    passwordTokenRepository.save(myToken);
	}
	
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
		
	}
	
	public String validatePasswordResetToken(long id, String token) {
	    PasswordResetToken passToken = 
	      passwordTokenRepository.findByToken(token);
	    if ((passToken == null) || (passToken.getUser()
	        .getId() != id)) {
	        return "invalidToken";
	    }
	 
	    Calendar cal = Calendar.getInstance();
	    if ((passToken.getExpiryDate()
	        .getTime() - cal.getTime()
	        .getTime()) <= 0) {
	        return "expired";
	    }
	 
	    User user = passToken.getUser();
	    Authentication auth = new UsernamePasswordAuthenticationToken(
	      user, null, Arrays.asList(
	      new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
	    SecurityContextHolder.getContext().setAuthentication(auth);
	    return null;
	}

}
