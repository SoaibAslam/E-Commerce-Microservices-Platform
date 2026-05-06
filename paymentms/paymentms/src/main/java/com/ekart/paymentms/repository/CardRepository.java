package com.ekart.paymentms.repository;

import com.ekart.paymentms.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
	List<Card> findByCustomerId(Long customerId);

	Card findByCardNumber(String cardNumber);
}