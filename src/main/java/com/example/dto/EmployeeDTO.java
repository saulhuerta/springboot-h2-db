package com.example.dto;

// Un Record ya tiene constructor, getters, equals, hashCode y toString automáticos. Es inmutable y limpio.
public record EmployeeDTO(Long id, String firstName, String lastName, String email, String departmentName) {
}
