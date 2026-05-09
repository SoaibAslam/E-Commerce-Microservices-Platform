package com.ekart.paymentms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.ekart.paymentms.feign")
@SpringBootApplication
public class PaymentmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentmsApplication.class, args);
	}

}
