package com.ekart.paymentms.service;

import com.ekart.paymentms.entity.Card;
import com.ekart.paymentms.feign.CustomerClient;
import com.ekart.paymentms.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository repository;

	@Autowired
	private CustomerClient customerClient;

	// ✅ HASH METHOD (ADD HERE)
	private String hashCvv(String cvv) {
		return Base64.getEncoder().encodeToString(cvv.getBytes());
	}

	@Override
	public Card saveCard(Card card) {

		// ✅ Validate customer exists
		try {
			customerClient.getCustomer(card.getCustomerId());
		} catch (Exception e) {
			throw new RuntimeException("Customer not found with id: " + card.getCustomerId());
		}

		// ✅ Hash CVV
		card.setHashedCvv(hashCvv(card.getHashedCvv()));

		return repository.save(card);
	}

	@Override
	public Card updateCard(Long id, Card newCard) {
		Card existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Card not found"));

		existing.setCardType(newCard.getCardType());
		existing.setCardNumber(newCard.getCardNumber());
		existing.setExpiry(newCard.getExpiry());
		existing.setNameOnCard(newCard.getNameOnCard());
		existing.setCustomerId(newCard.getCustomerId());

		// ✅ HASH CVV AGAIN ON UPDATE
		existing.setHashedCvv(hashCvv(newCard.getHashedCvv()));

		return repository.save(existing);
	}

	@Override
	public void deleteCard(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Card getCardById(Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Card not found"));
	}

	@Override
	public List<Card> getCardsByCustomer(Long customerId) {
		return repository.findByCustomerId(customerId);
	}
}