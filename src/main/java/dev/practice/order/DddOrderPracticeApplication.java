package dev.practice.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DddOrderPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DddOrderPracticeApplication.class, args);
	}

}
