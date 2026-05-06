package com.ekart.paymentms.service;

public interface PaymentService {
	String pay(Long customerId, String cardNumber, String cvv, Double amount);
}
