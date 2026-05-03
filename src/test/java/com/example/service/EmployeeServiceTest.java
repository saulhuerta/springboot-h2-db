package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domain.Department;
import com.example.domain.Employee;
import com.example.dto.EmployeeDTO;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@Mock
	private DepartmentRepository departmentRepository;

	@InjectMocks
	private EmployeeService employeeService;

	@Test
	void saveEmployee_success() {

		// 1. GIVEN (Preparación)
		// Creamos el DTO de entrada (Record)
		EmployeeDTO inputDto = new EmployeeDTO(null, "Pedro", "Paramo", "pedro@dev.com", "Engineering");

		// Simulamos el departamento que el repo debería encontrar
		Department dept = new Department("Engineering");

		// Simulamos el empleado que el repo guardaría (con ID generado)
		Employee savedEmployee = new Employee("Pedro", "Paramo", "pedro@dev.com", dept);

		when(departmentRepository.findByName("Engineering")).thenReturn(Optional.of(dept));
		when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);

		// 2. WHEN (Acción)
		EmployeeDTO result = employeeService.save(inputDto);

		// 3. THEN (Verificación)
		assertNotNull(result);
		assertEquals("Pedro", result.firstName());
		assertEquals("Engineering", result.departmentName());

		// Verificamos que se llamó al repo de departamentos una vez
		verify(departmentRepository, times(1)).findByName("Engineering");
		verify(employeeRepository, times(1)).save(any(Employee.class));
	}

}
