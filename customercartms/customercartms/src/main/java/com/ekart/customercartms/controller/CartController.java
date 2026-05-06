package com.ekart.customercartms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ekart.customercartms.entity.CartItem;
import com.ekart.customercartms.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService service;

	@GetMapping("/all")
	public String showWelcomeMessage() {
		return "Welcome to cart Service";
	}

	@PostMapping("/{email}")
	public String addItem(@PathVariable String email, @RequestBody CartItem item) {
		return service.addItem(email, item);
	}

	@PutMapping("/{email}/{productId}/{qty}")
	public String modifyItem(@PathVariable String email, @PathVariable Integer productId, @PathVariable Integer qty) {
		return service.updateItem(email, productId, qty);
	}

	@DeleteMapping("/{email}/{productId}")
	public String deleteItem(@PathVariable String email, @PathVariable Integer productId) {
		return service.deleteItem(email, productId);
	}

	@GetMapping("/{email}")
	public List<CartItem> viewCart(@PathVariable String email) {
		return service.viewCart(email);
	}
}