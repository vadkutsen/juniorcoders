package com.vadkutsen.juniorcoders.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	
	@RequestMapping("/")
	public String SayHello() {
		return "index";
	}
}
