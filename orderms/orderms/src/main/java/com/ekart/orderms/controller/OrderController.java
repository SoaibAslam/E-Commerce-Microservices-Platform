package com.ekart.orderms.controller;

import com.ekart.orderms.entity.Order;
import com.ekart.orderms.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService service;

	public OrderController(OrderService service) {
		this.service = service;
	}

	// Create Order
	@PostMapping
	public Order createOrder(@RequestBody Order order, @RequestParam String cvv) {
		return service.createOrder(order, cvv);
	}

	// Get All Orders
	@GetMapping
	public List<Order> getAllOrders() {
		return service.getAllOrders();
	}

	// Get Order By ID
	@GetMapping("/{id}")
	public Order getOrder(@PathVariable Long id) {
		return service.getOrderById(id);
	}
}