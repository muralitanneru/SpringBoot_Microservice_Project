package com.example.department_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.department_service.Entity.Department;
import com.example.department_service.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);	
	}

	public Department findDepartmentById(Long id) {
		return departmentRepository.findById(id).orElse(null);
	}
	

}
