package com.ekart.orderms.feign;

import com.ekart.orderms.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENTMS")
public interface PaymentClient {

	@PostMapping("/payment/pay")
	String makePayment(@RequestBody PaymentRequest request);
}