package com.example.department_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.department_service.Entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

	 //Department findDepartmentById(Long id);
}
