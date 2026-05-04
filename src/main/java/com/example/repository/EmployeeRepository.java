package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByDepartmentName(String departmentName);

	// Spring genera: SELECT COUNT(*) FROM employees WHERE department_name = ?
	long countByDepartmentName(String departmentName);

}
