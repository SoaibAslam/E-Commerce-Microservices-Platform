package com.ekart.paymentms.controller;

import com.ekart.paymentms.dto.PaymentRequest;
import com.ekart.paymentms.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@GetMapping
	public String welcome() {
		return "Payment Service is running";
	}

	@PostMapping("/pay")
	public String pay(@RequestBody PaymentRequest request) {
		return paymentService.pay(request.getCustomerId(), request.getCardNumber(), request.getCvv(),
				request.getAmount());
	}
}