package com.abrajner.plagiarismdetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PlagiarismDetectorApplication {
	
	@RequestMapping("/hello")
	public String home() {
		return "Hello";
	}

	@RequestMapping("/bye")
	public String bye() {
		return "Bye";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PlagiarismDetectorApplication.class, args);
	}
	
}