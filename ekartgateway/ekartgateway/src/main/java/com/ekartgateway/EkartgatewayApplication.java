package com.ekartgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EkartgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkartgatewayApplication.class, args);
	}

}
