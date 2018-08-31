package com.gmm.base.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorsGreetingsApplicationController {
	
	// This function returns greetings with the name value
	@GetMapping("/greet")
	public String greetUser(@RequestParam("name") String name) {	
		return "Warm welcomes "+name;
	}

}