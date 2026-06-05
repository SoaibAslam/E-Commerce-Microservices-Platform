package com.inventoryms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.inventoryms.dto.InventoryRequest;
import com.inventoryms.entity.Inventory;
import com.inventoryms.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryService service;

	@PostMapping("/create")
	public Inventory create(@RequestBody InventoryRequest request) {

		return service.createStock(request.getProductId(), request.getQuantity());
	}

	@GetMapping("/{productId}")
	public Inventory getStock(@PathVariable Long productId) {

		return service.getStock(productId);
	}

	@GetMapping("/check/{productId}/{qty}")
	public boolean checkStock(@PathVariable Long productId, @PathVariable int qty) {

		return service.isInStock(productId, qty);
	}

	@PostMapping("/reduce")
	public String reduce(@RequestBody InventoryRequest request) {

		service.reduceStock(request.getProductId(), request.getQuantity());

		return "Stock reduced successfully";
	}

	@PostMapping("/add")
	public String add(@RequestBody InventoryRequest request) {

		service.addStock(request.getProductId(), request.getQuantity());

		return "Stock added successfully";
	}
}