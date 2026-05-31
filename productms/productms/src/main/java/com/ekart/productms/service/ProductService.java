package com.ekart.productms.service;

import java.util.List;

import com.ekart.productms.Dto.ProductDTO;
import com.ekart.productms.entity.Product;

public interface ProductService {

	List<Product> getAllProducts();

	Product getProductById(Integer productId);

	Product createProduct(ProductDTO productDTO);

	Product updateProduct(ProductDTO productDTO);

	void deleteProduct(Integer productId);

	List<Product> saveAllProducts(List<ProductDTO> products);
}