package com.sutaruhin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sutaruhin.entity.Authentication;
import com.sutaruhin.service.AuthenticationService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping("/list")
	public String getList(Model model) {
		model.addAttribute("authentications", authenticationService.getAuthentications());

		return "employee/employeeList";
	}

	@PostMapping("/details")
	public String details(@RequestParam("id") Integer id, Model model) {
		Authentication authentication = authenticationService.getAuthenticationById(id);
		model.addAttribute("authentication", authentication);

		return "employee/details";
	}

	@GetMapping("/register")
	public String getRegister(@ModelAttribute Authentication authentication) {
		return "employee/register";
	}

	@PostMapping("/register")
	public String postRegister(@Validated @ModelAttribute Authentication authentication, BindingResult result) {
	    if (result.hasErrors()) {
	        return "employee/register";
	    }

	    try {
	        authenticationService.saveAuthentication(authentication);
	    } catch (IllegalArgumentException e) {
	        result.rejectValue("code", "error.authentication", "この社員番号は存在しています");
	        return "employee/register";
	    }

		return "redirect:/employee/list";
	}

	@GetMapping("/update")
	public String getUpdate(@RequestParam("id") Integer id, Model model) {
		Authentication authentication = authenticationService.getAuthenticationById(id);
		model.addAttribute("authentication", authentication);

		return "employee/update";
	}

	@PostMapping("/update")
	public String postUpdate(@Validated Authentication authenticaion, BindingResult result) {
		if(result.hasErrors()) {
			return "employee/update";
		}
		try {
			authenticationService.updateAuthenticaion(authenticaion);
	    } catch (IllegalArgumentException e) {
	        result.rejectValue("code", "error.authentication", "この社員番号は存在しています");
	        return "employee/update";
	    }

		return "redirect:/employee/list";
	}

	@PostMapping("/delete")
	public String postDelete(@RequestParam("id") Integer id) {
		Authentication authentication = authenticationService.getAuthenticationById(id);
		authenticationService.deleteAuthentication(authentication);

		return "redirect:/employee/list";
	}

}
