package com.inventoryms.service;

import com.inventoryms.entity.Inventory;

public interface InventoryService {

	Inventory createStock(Long productId, Integer qty);

	boolean isInStock(Long productId, int requiredQty);

	void reduceStock(Long productId, int qty);

	void addStock(Long productId, int qty);

	Inventory getStock(Long productId);
}