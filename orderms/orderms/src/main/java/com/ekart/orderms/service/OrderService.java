package com.ekart.orderms.service;

import com.ekart.orderms.entity.Order;

import java.util.List;

public interface OrderService {

	Order createOrder(Order order, String cvv);

	List<Order> getAllOrders();

	Order getOrderById(Long id);
}