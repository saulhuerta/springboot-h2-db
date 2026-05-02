package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	// Spring infiere la query: SELECT * FROM department WHERE name = ?
	Optional<Department> findByName(String name);
}
