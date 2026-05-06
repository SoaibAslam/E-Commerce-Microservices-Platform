package com.ekart.paymentms.service;

import java.util.List;

import com.ekart.paymentms.entity.Card;

public interface CardService {

	Card saveCard(Card card);

	Card updateCard(Long id, Card card);

	void deleteCard(Long id);

	Card getCardById(Long id);

	List<Card> getCardsByCustomer(Long customerId);

}
