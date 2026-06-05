package com.inventoryms.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Map<String, Object>> product(ProductNotFoundException ex) {

		Map<String, Object> map = new HashMap<>();
		map.put("status", 404);
		map.put("message", ex.getMessage());

		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InventoryNotFoundException.class)
	public ResponseEntity<Map<String, Object>> inventory(InventoryNotFoundException ex) {

		Map<String, Object> map = new HashMap<>();
		map.put("status", 404);
		map.put("message", ex.getMessage());

		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<Map<String, Object>> stock(InsufficientStockException ex) {

		Map<String, Object> map = new HashMap<>();
		map.put("status", 400);
		map.put("message", ex.getMessage());

		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
}