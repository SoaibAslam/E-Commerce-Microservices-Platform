package com.inventoryms.exception;

public class InventoryNotFoundException extends RuntimeException {

	public InventoryNotFoundException(String message) {
		super(message);
	}
}