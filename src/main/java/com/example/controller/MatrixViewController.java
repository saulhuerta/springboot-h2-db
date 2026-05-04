package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.EmployeeService;

@Controller
@RequestMapping("/")
public class MatrixViewController {

	private final EmployeeService employeeService;

	public MatrixViewController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// @GetMapping("/dashboard")
	public String showDashboard(Model model) {
		model.addAttribute("employees", employeeService.findAll());
		return "index";
	}

}
