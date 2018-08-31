package com.gmm.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.gmm.config","com.gmm.base.rest","com.ge.predix.web.cors"})
public class CorsGreetingsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CorsGreetingsApplication.class, args);
	}
}
