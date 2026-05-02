package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.DepartmentDTO;
import com.example.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	private final DepartmentService departmentService;

	// Inyección por constructor: El estándar de oro en Spring
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	// GET /api/departments
	@GetMapping
	public List<DepartmentDTO> getAll() {
		return departmentService.findAll();
	}

	// POST /api/departments
	@PostMapping
	public ResponseEntity<DepartmentDTO> create(@RequestBody String name) {
		// Tip: Aquí recibimos solo el nombre como String para simplificar,
		// pero lo ideal sería un Record si el objeto creciera.
		DepartmentDTO saved = departmentService.save(name);
		return ResponseEntity.ok(saved);
	}
}
