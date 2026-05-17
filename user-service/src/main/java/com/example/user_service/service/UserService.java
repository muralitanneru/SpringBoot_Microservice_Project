package com.example.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.user_service.VO.Department;
import com.example.user_service.VO.ResponseTemplate;
import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	public User SaveUser(User user) {
		
		return userRepository.save(user);
	}

	public ResponseTemplate getUserWithDepartment(Long userId) {
		
		ResponseTemplate responseTemplate = new ResponseTemplate();
		User user=userRepository.findByUserId(userId);
		
//		Department department = restTemplate.
//				getForObject("http://localhost:9001/departments/" + user.getDepartmentId(), Department.class);
		Department department = restTemplate.
				getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);
		
		
		responseTemplate.setUser(user);
		responseTemplate.setDepartment(department);
		return responseTemplate;			
		
	}
}
