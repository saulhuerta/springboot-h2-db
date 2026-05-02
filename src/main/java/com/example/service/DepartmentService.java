package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Department;
import com.example.dto.DepartmentDTO;
import com.example.repository.DepartmentRepository;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	// Inyección por constructor: El estándar de oro en Spring
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	public DepartmentDTO save(String name) {
		Department dept = new Department(name);
		Department saved = departmentRepository.save(dept);
		return new DepartmentDTO(saved.getId(), saved.getName(), 0);
	}

	public List<DepartmentDTO> findAll() {
		return departmentRepository.findAll().stream().map(dept -> new DepartmentDTO(dept.getId(), dept.getName(),
				dept.getEmployees() != null ? dept.getEmployees().size() : 0)).toList();
	}
}
