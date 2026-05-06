package com.ekart.orderms.dto;

public class PaymentRequest {

	private Long customerId;
	private String cardNumber;
	private String cvv;
	private Double amount;

	// getters & setters

	public Long getCustomerId() {
		return customerId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public Double getAmount() {
		return amount;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}