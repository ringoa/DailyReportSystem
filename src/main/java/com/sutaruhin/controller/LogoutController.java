package com.sutaruhin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {

	@PostMapping("/logout")
	public String postLogput() {

		return "redirect:/login";
	}
}
