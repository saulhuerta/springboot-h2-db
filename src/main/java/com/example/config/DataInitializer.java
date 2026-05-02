package com.example.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.domain.Department;
import com.example.domain.Employee;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner initDatabase(DepartmentRepository deptRepo, EmployeeRepository empRepo) {
		return args -> {
			// 1. Crear Departamentos
			Department dev = new Department("Engineering");
			Department cyber = new Department("Cybersecurity");

			deptRepo.save(dev);
			deptRepo.save(cyber);

			// 2. Crear Empleados vinculados
			Employee e1 = new Employee("Saul", "Huerta", "saul@omnisys.com", dev);
			Employee e2 = new Employee("Jane", "Doe", "jane.doe@cyber.com", cyber);

			empRepo.save(e1);
			empRepo.save(e2);

			System.out.println(">>> Base de datos H2 inicializada con éxito.");
		};
	}

}
