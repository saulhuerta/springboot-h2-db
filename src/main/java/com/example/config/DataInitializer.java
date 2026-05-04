package com.example.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.domain.Department;
import com.example.domain.Employee;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {
	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;

	public DataInitializer(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
		this.departmentRepository = departmentRepository;
		this.employeeRepository = employeeRepository;
	}

	@Override
	@Transactional
	public void run(String... args) {

		if (departmentRepository.count() == 0) {
			// 1. Guardamos y recuperamos las instancias persistidas
			Department arch = departmentRepository.save(new Department("ARCHITECTURE"));
			Department cyber = departmentRepository.save(new Department("CYBERSECURITY"));
			Department support = departmentRepository.save(new Department("SUPPORT"));
			departmentRepository.save(new Department("HELP DESK"));

			// 2. Usamos esos objetos ya persistidos para los empleados
			Employee e1 = new Employee("Pedro", "Paramo", "pedro@omnisys.com", arch);
			Employee e2 = new Employee("Jaime", "Chavez", "jane.doe@cyber.com", support);

			employeeRepository.save(e1);
			employeeRepository.save(e2);

			System.out.println(">> DATOS CARGADOS EN LA MATRIX");
		}
	}
}
