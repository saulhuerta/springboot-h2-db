package com.example.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.domain.Department;
import com.example.domain.Employee;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

/*
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

*/

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