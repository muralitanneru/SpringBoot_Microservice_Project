package com.example.security_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security_service.Entity.UserCredentials;
import com.example.security_service.Repository.UserCredentialRepository;

@Service
public class AuthenticationSerivce {
	
	@Autowired
	private UserCredentialRepository userCredentialRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	
	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}
	
	public boolean validateToken(String token) {
		try {
			jwtService.validateToken(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String saveUser(UserCredentials userCredentials) {
		userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
		userCredentialRepository.save(userCredentials);
		return "User saved successfully";
	}
	
	
	

}
