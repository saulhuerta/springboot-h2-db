package com.example.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorController {

	@ExceptionHandler(Exception.class)
	public String handleAllErrors(Exception ex, Model model) {
		model.addAttribute("errorMessage", "ERROR EN EL ENLACE: " + ex.getMessage());
		return "error-matrix"; // Creamos un HTML especial para esto
	}
}