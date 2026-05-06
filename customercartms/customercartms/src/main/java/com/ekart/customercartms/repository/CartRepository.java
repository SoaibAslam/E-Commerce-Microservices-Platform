package com.ekart.customercartms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ekart.customercartms.entity.CartItem;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Integer> {

	List<CartItem> findByCustomerEmail(String email);

	CartItem findByCustomerEmailAndProductId(String customerEmail, Integer productId);
}
