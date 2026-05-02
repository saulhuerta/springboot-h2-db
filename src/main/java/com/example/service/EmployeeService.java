package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Department;
import com.example.domain.Employee;
import com.example.dto.EmployeeDTO;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;

	// Inyección por constructor: El estándar de oro en Spring
	public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
	}

	public EmployeeDTO save(EmployeeDTO dto) {
		Department department = departmentRepository.findByName(dto.departmentName())
				.orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

		// Mapeo directo y limpio desde el Record
		Employee newEmployee = new Employee(dto.firstName(), dto.lastName(), dto.email(), department);

		Employee saved = employeeRepository.save(newEmployee);

		return new EmployeeDTO(saved.getId(), saved.getFirstName(), saved.getLastName(), saved.getEmail(),
				saved.getDepartment().getName());
	}

	public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
		// 1. Buscar el empleado existente
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));

		// 2. Si el departamento cambió en el DTO, buscamos el nuevo objeto Department
		if (!existingEmployee.getDepartment().getName().equals(dto.departmentName())) {
			Department newDept = departmentRepository.findByName(dto.departmentName())
					.orElseThrow(() -> new RuntimeException("Departamento no encontrado: " + dto.departmentName()));
			existingEmployee.setDepartment(newDept);
		}

		// 3. Actualización de campos manual (Anti-Lombok)
		existingEmployee.setFirstName(dto.firstName());
		existingEmployee.setLastName(dto.lastName());
		existingEmployee.setEmail(dto.email());

		// 4. Persistencia del cambio
		Employee updated = employeeRepository.save(existingEmployee);

		// 5. Retornamos la nueva "foto" inmutable
		return new EmployeeDTO(updated.getId(), updated.getFirstName(), updated.getLastName(), updated.getEmail(),
				updated.getDepartment().getName());
	}

	public List<EmployeeDTO> findAll() {
		return employeeRepository.findAll().stream().map(emp -> new EmployeeDTO(emp.getId(), emp.getFirstName(),
				emp.getLastName(), emp.getEmail(), emp.getDepartment().getName())).toList();
	}
}
