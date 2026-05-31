package com.ekart.productms.controller;

import com.ekart.productms.Dto.ProductDTO;
import com.ekart.productms.entity.Product;
import com.ekart.productms.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(productService.getProductById(id));
		} catch (NoSuchElementException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO) {

		try {
			Product product = productService.createProduct(productDTO);

			return ResponseEntity.status(HttpStatus.CREATED).body(product);

		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDTO productDTO) {

		try {
			return ResponseEntity.ok(productService.updateProduct(productDTO));

		} catch (NoSuchElementException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {

		try {
			productService.deleteProduct(id);

			return ResponseEntity.ok("Product deleted successfully");

		} catch (NoSuchElementException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	@PostMapping("/bulk")
	public ResponseEntity<?> saveAllProducts(@Valid @RequestBody List<ProductDTO> products) {

		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveAllProducts(products));

		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
}