package com.sutaruhin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LogingController {

	@GetMapping
	public String showList() {
		return "login";
	}
}
