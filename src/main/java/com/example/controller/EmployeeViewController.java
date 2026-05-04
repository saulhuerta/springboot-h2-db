package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.EmployeeDTO;
import com.example.service.DepartmentService;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/view/employees")
public class EmployeeViewController {

	private final EmployeeService employeeService;

	private final DepartmentService departmentService;

	public EmployeeViewController(EmployeeService employeeService, DepartmentService departmentService) {
		this.employeeService = employeeService;
		this.departmentService = departmentService;
	}

	// Listado principal
	@GetMapping
	public String listEmployees(Model model) {
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("currentView", "employees");
		return "employees/list";
	}

	// Mostrar formulario de registro
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("employee", new EmployeeDTO(null, "", "", "", ""));

		// Enviamos todos los departamentos disponibles a la vista
		model.addAttribute("departments", departmentService.findAll());

		return "employees/add-employee";
	}

	// Procesar el guardado y redireccionar
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") EmployeeDTO employeeDTO) {
		employeeService.save(employeeDTO);
		return "redirect:/view/employees"; // Regresa a la lista tras guardar
	}
}
