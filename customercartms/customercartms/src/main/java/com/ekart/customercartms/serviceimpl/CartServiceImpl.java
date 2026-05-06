package com.ekart.customercartms.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ekart.customercartms.DTO.ProductDTO;
import com.ekart.customercartms.entity.CartItem;
import com.ekart.customercartms.repository.CartRepository;
import com.ekart.customercartms.service.CartService;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CartRepository repo;

	@Override
	public String addItem(String email, CartItem item) {

		if (item.getProductId() == null || item.getQuantity() == null) {
			throw new RuntimeException("ProductId and Quantity are required");
		}

		// ✅ FIX: correct DTO type
		ProductDTO product = fetchProduct(item.getProductId());

		if (product == null) {
			throw new RuntimeException("Product not found");
		}

		CartItem existing = repo.findByCustomerEmailAndProductId(email, item.getProductId());

		if (existing != null) {
			existing.setQuantity(existing.getQuantity() + item.getQuantity());
			repo.save(existing);
			return "Cart updated with additional quantity";
		}

		item.setCustomerEmail(email);
		repo.save(item);

		return "Item added to cart";
	}

	private ProductDTO fetchProduct(Integer productId) {
		try {
			String url = "http://PRODUCTMS/products/" + productId;
			return restTemplate.getForObject(url, ProductDTO.class);
		} catch (Exception e) {
			e.printStackTrace(); // IMPORTANT for debugging
			return null;
		}
	}

	@Override
	public String updateItem(String email, Integer productId, Integer qty) {

		CartItem existing = repo.findByCustomerEmailAndProductId(email, productId);

		if (existing == null) {
			throw new RuntimeException("Item not found in cart");
		}

		if (qty == 0) {
			repo.delete(existing);
			return "Item removed from cart";
		}

		existing.setQuantity(qty);
		repo.save(existing);

		return "Item quantity updated";
	}

	@Override
	public String deleteItem(String email, Integer productId) {

		CartItem existing = repo.findByCustomerEmailAndProductId(email, productId);

		if (existing == null) {
			throw new RuntimeException("Item not found");
		}

		repo.delete(existing);
		return "Item deleted from cart";
	}

	@Override
	public List<CartItem> viewCart(String email) {
		return repo.findByCustomerEmail(email);
	}
}