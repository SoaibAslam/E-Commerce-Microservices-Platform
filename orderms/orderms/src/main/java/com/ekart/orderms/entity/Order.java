package com.ekart.orderms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // ✅ Primary Key

	private Double amount;

	@Column(name = "customer_id")
	private Long customerId;

	private String status;

	@Column(name = "card_number")
	private String cardNumber;

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public String getStatus() {
		return status;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}