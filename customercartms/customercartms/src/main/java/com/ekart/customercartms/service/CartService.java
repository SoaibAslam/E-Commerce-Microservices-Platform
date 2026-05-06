package com.ekart.customercartms.service;

import java.util.List;

import com.ekart.customercartms.entity.CartItem;

public interface CartService {

	String addItem(String email, CartItem item);

	String updateItem(String email, Integer productId, Integer qty);

	String deleteItem(String email, Integer productId);

	List<CartItem> viewCart(String email);
}
