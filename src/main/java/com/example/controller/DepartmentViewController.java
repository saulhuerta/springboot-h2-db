package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.DepartmentDTO;
import com.example.service.DepartmentService;

@Controller
@RequestMapping("/view/departments")
public class DepartmentViewController {

	private final DepartmentService departmentService;

	public DepartmentViewController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("departments", departmentService.findAll());
		model.addAttribute("currentView", "departments");
		return "department/list";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("department", new DepartmentDTO(null, "", 0));
		return "department/add-department";
	}

	@PostMapping("/save")
	public String save(@RequestParam("name") String name) {
		departmentService.save(name);
		return "redirect:/view/departments";
	}
}
