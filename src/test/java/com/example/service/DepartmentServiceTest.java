package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domain.Department;
import com.example.dto.DepartmentDTO;
import com.example.repository.DepartmentRepository;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

	@Mock
	DepartmentRepository departmentRepository;

	@InjectMocks
	DepartmentService departmentService;

	@Test
	void saveDepartment_success() {

		// 1. GIVEN (Preparación)
		String nombreDepto = "Engineering";
		Department deptToSave = new Department(nombreDepto);

		// Simulamos que al guardar, el DB nos regresa la entidad con un ID (ej. 1L)
		Department savedDept = new Department(nombreDepto);

		// Mockito: El save devuelve la ENTIDAD, no Optional
		when(departmentRepository.save(any(Department.class))).thenReturn(savedDept);

		// 2. WHEN (Acción)
		DepartmentDTO result = departmentService.save(nombreDepto);

		// 3. THEN (Verificación)
		assertNotNull(result);
		assertEquals(nombreDepto, result.name()); //

		// Verificamos que se llamó al repo de departamentos una vez
		verify(departmentRepository, times(1)).save(any(Department.class));
	}

}
