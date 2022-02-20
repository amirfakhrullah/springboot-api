package com.springboot.api.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student amir = new Student("Amir", LocalDate.of(1996, Month.JANUARY, 29), "amirfakhrullah96@gmail.com");
			
			Student alex = new Student("Alex", LocalDate.of(1998, Month.FEBRUARY, 2), "alex@gmail.com");
			
			repository.saveAll(
					List.of(amir, alex)
					);
		};
	}
}
