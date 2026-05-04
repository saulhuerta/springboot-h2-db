package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Department;
import com.example.dto.DepartmentDTO;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;

	// Inyección por constructor: El estándar de oro en Spring
	public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
		this.departmentRepository = departmentRepository;
		this.employeeRepository = employeeRepository;
	}

	public DepartmentDTO save(String name) {
		Department dept = new Department(name);
		Department saved = departmentRepository.save(dept);
		return new DepartmentDTO(saved.getId(), saved.getName(), 0);
	}

	public DepartmentDTO save(DepartmentDTO depto) {
		Department dept = new Department(depto.name());
		Department saved = departmentRepository.save(dept);
		return new DepartmentDTO(saved.getId(), saved.getName(), 0);
	}

	public List<DepartmentDTO> findAll() {
		return departmentRepository.findAll().stream().map(dept -> {
			long totalAgentes = employeeRepository.countByDepartmentName(dept.getName());
			return new DepartmentDTO(dept.getId(), dept.getName(), (int) totalAgentes);
		}).toList();
	}
}
