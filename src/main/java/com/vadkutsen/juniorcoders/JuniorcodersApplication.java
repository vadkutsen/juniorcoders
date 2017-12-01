package com.vadkutsen.juniorcoders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.vadkutsen.juniorcoders.backend.persistence.repositories")
public class JuniorcodersApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuniorcodersApplication.class, args);
	}
}
