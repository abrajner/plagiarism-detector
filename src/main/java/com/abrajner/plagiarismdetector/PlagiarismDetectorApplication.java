package com.abrajner.plagiarismdetector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abrajner.plagiarismdetector.dao.entity.UserEntity;
import com.abrajner.plagiarismdetector.dao.repository.UserRepository;

@SpringBootApplication
@RestController
public class PlagiarismDetectorApplication {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/test")
	public String home() {
		final UserEntity user = new UserEntity.Builder()
				.email("ala.brajner")
				.firstName("ala")
				.lastName("brajner")
				.login("abrajner")
				.password("passwd")
				.build();
		this.userRepository.save(user);
		
		return String.valueOf(this.userRepository.count());
	}
	
	public static void main(final String[] args) {
		SpringApplication.run(PlagiarismDetectorApplication.class, args);
	}
}