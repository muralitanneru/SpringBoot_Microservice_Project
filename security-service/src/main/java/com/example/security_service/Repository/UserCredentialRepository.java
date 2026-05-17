package com.example.security_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security_service.Entity.UserCredentials;

public interface UserCredentialRepository extends JpaRepository<UserCredentials, Integer> {
	UserCredentials findByUsername(String username);
	
}
