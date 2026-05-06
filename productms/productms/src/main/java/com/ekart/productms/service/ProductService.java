package com.ekart.productms.service;

import com.ekart.productms.Dto.ProductDTO;
import com.ekart.productms.entity.Product;

import java.util.List;

public interface ProductService {

	List<Product> getAllProducts();

	Product getProductById(Integer productId);

	Product createProduct(ProductDTO productDTO);

	Product updateProduct(ProductDTO productDTO);

	String reduceQuantity(Integer productId, Integer qty);
	
	void deleteProduct(Integer productId);

	List<Product> saveAllProducts(List<ProductDTO> products);
}
