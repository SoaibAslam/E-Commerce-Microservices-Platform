package com.ekart.paymentms.service;

import com.ekart.paymentms.entity.Card;
import com.ekart.paymentms.entity.Payment;
import com.ekart.paymentms.exception.InvalidCvvException;
import com.ekart.paymentms.repository.CardRepository;
import com.ekart.paymentms.repository.PaymentRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Base64;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	@CircuitBreaker(name = "paymentService", fallbackMethod = "fallback")
	public String pay(Long customerId, String cardNumber, String cvv, Double amount) {

		Card card = cardRepository.findByCardNumber(cardNumber);

		if (card == null)
			return "Card not found";

		if (!card.getCustomerId().equals(customerId))
			return "Card does not belong to customer";

		String hashedInput = Base64.getEncoder().encodeToString(cvv.getBytes());

		if (!hashedInput.equals(card.getHashedCvv())) {
			throw new InvalidCvvException("Invalid CVV");
		}

		if (isExpired(card.getExpiry()))
			return "Card expired";

		// SAVE PAYMENT
		Payment payment = new Payment();
		payment.setCustomerId(customerId);
		payment.setCardNumber(cardNumber);
		payment.setAmount(amount);
		payment.setStatus("SUCCESS");
		payment.setTimestamp(LocalDateTime.now());

		paymentRepository.save(payment);

		return "Payment successful ₹" + amount;
	}

	private boolean isExpired(String expiry) {
		String[] parts = expiry.split("/");
		return YearMonth.of(Integer.parseInt(parts[1]), Integer.parseInt(parts[0])).isBefore(YearMonth.now());
	}

	public String fallback(Long customerId, String cardNumber, String cvv, Double amount, Throwable t) {
		return "Too many failed attempts. Try later.";
	}
}