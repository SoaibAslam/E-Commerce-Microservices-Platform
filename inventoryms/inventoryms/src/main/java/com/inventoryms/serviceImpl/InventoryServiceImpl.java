package com.inventoryms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventoryms.entity.Inventory;
import com.inventoryms.feign.ProductClient;
import com.inventoryms.repository.InventoryRepository;
import com.inventoryms.service.InventoryService;
import com.inventoryms.exception.*;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository repository;

	@Autowired
	private ProductClient productClient;

	private void validateProduct(Long productId) {

		try {
			productClient.getProductById(productId.intValue());

		} catch (Exception e) {
			throw new ProductNotFoundException("Product not found with id: " + productId);
		}
	}

	@Override
	public Inventory createStock(Long productId, Integer qty) {

		validateProduct(productId);

		Inventory inventory = new Inventory();

		inventory.setProductId(productId);
		inventory.setQuantity(qty);

		return repository.save(inventory);
	}

	@Override
	public boolean isInStock(Long productId, int requiredQty) {

		validateProduct(productId);

		Inventory inventory = repository.findByProductId(productId)
				.orElseThrow(() -> new InventoryNotFoundException("Inventory not found for product id: " + productId));

		return inventory.getQuantity() >= requiredQty;
	}

	@Override
	public void reduceStock(Long productId, int qty) {

		validateProduct(productId);

		Inventory inventory = repository.findByProductId(productId)
				.orElseThrow(() -> new RuntimeException("Product not found in inventory"));

		if (inventory.getQuantity() < qty) {
			throw new InsufficientStockException("Insufficient stock");
		}

		inventory.setQuantity(inventory.getQuantity() - qty);

		repository.save(inventory);
	}

	@Override
	public void addStock(Long productId, int qty) {

		validateProduct(productId);

		Inventory inventory = repository.findByProductId(productId)
				.orElseThrow(() -> new RuntimeException("Product not found in inventory"));

		inventory.setQuantity(inventory.getQuantity() + qty);

		repository.save(inventory);
	}

	@Override
	public Inventory getStock(Long productId) {

		validateProduct(productId);

		return repository.findByProductId(productId)
				.orElseThrow(() -> new RuntimeException("Product not found in inventory"));
	}
}