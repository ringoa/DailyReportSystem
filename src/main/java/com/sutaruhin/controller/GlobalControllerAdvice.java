package com.sutaruhin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sutaruhin.entity.Employee;
import com.sutaruhin.service.AuthenticationService;
import com.sutaruhin.service.EmployeeService;

@ControllerAdvice
public class GlobalControllerAdvice {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private EmployeeService employeeService;

	@ModelAttribute("fullName")
	public String addFullName(Principal principal) {
		if (principal == null) {
	        return ""; //
	    }

		Integer employeeId = authenticationService.findByUsername(principal.getName());
		Employee employee = employeeService.findById(employeeId);

		return employee.getName();
	}
}
