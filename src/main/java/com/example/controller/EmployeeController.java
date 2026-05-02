package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EmployeeDTO;
import com.example.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	// Inyección por constructor: El estándar de oro en Spring
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// GET /api/employees
	@GetMapping
	public List<EmployeeDTO> getAll() {
		return employeeService.findAll();
	}

	// POST /api/employees
	@PostMapping
	public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO dto) {
		// En un microservicio real, aquí aplicarías @Valid para Ciberseguridad
		EmployeeDTO saved = employeeService.save(dto);
		return ResponseEntity.ok(saved);
	}

	// PUT /api/employees/{id}
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> update(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
		EmployeeDTO updated = employeeService.updateEmployee(id, dto);
		return ResponseEntity.ok(updated);
	}
}
