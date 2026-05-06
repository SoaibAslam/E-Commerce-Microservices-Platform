package com.ekart.paymentms.controller;

import com.ekart.paymentms.entity.Card;
import com.ekart.paymentms.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment/cards")
public class CardController {

	@Autowired	
	private CardService service;

	@GetMapping
	public String welcome() {
		return "Card Service running";
	}

	@PostMapping
	public Card addCard(@RequestBody Card card) {
		return service.saveCard(card);
	}

	@PutMapping("/{id}")
	public Card updateCard(@PathVariable Long id, @RequestBody Card card) {
		return service.updateCard(id, card);
	}

	@DeleteMapping("/{id}")
	public String deleteCard(@PathVariable Long id) {
		service.deleteCard(id);
		return "Deleted successfully";
	}

	@GetMapping("/{id}")
	public Card getCard(@PathVariable Long id) {
		return service.getCardById(id);
	}

	@GetMapping("/customer/{customerId}")
	public List<Card> getCards(@PathVariable Long customerId) {
		return service.getCardsByCustomer(customerId);
	}
}