package com.example.security_service.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.security_service.Entity.UserCredentials;
import com.example.security_service.Repository.UserCredentialRepository;
import com.example.security_service.config.CustomUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserCredentialRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
	        throws UsernameNotFoundException {

	    UserCredentials credentials =
	            userRepository.findByUsername(username);

	    if (credentials == null) {

	        throw new UsernameNotFoundException(
	                "User not found with username: " + username);
	    }

	    return new CustomUserDetails(credentials);
	}
	
	

}
