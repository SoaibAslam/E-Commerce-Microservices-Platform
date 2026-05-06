package com.ekart.orderms.serviceImpl;

import com.ekart.orderms.dto.PaymentRequest;
import com.ekart.orderms.entity.Order;
import com.ekart.orderms.feign.PaymentClient;
import com.ekart.orderms.repository.OrderRepository;
import com.ekart.orderms.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository repository;
	private final PaymentClient paymentClient;

	public OrderServiceImpl(OrderRepository repository, PaymentClient paymentClient) {
		this.repository = repository;
		this.paymentClient = paymentClient;
	}

	@Override
	public Order createOrder(Order order, String cvv) {

		// Step 1: Save order
		order.setStatus("CREATED");
		Order saved = repository.save(order);

		// Step 2: Call PaymentMS
		PaymentRequest request = new PaymentRequest();
		request.setCustomerId(saved.getCustomerId()); // ✅ correct
		request.setCardNumber(saved.getCardNumber());
		request.setCvv(cvv);
		request.setAmount(saved.getAmount());

		String response = paymentClient.makePayment(request);

		// Step 3: Update status
		if (response.contains("successful")) {
			saved.setStatus("PAID");
		} else {
			saved.setStatus("FAILED");
		}

		return repository.save(saved);
	}

	@Override
	public List<Order> getAllOrders() {
		return repository.findAll();
	}

	@Override
	public Order getOrderById(Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
	}
}