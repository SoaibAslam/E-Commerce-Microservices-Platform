package com.soaib.authms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AuthmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthmsApplication.class, args);
	}

}
