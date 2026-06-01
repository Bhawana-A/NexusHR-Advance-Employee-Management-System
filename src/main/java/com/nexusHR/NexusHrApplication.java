package com.nexusHR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Iske bina @CreatedDate kaam nahi karega
public class NexusHrApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(NexusHrApplication.class, args);
	}

}
