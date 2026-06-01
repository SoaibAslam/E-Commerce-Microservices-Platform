package com.ekart.productms.serviceimpl;

import com.ekart.productms.Dto.InventoryRequest;
import com.ekart.productms.Dto.ProductDTO;
import com.ekart.productms.client.InventoryClient;
import com.ekart.productms.entity.Product;
import com.ekart.productms.repository.ProductRepository;
import com.ekart.productms.service.ProductService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	private final InventoryClient inventoryClient;

	public ProductServiceImpl(ProductRepository productRepository, InventoryClient inventoryClient) {
		this.productRepository = productRepository;
		this.inventoryClient = inventoryClient;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Integer productId) {

		return productRepository.findById(productId)
				.orElseThrow(() -> new NoSuchElementException("Product not found with id: " + productId));
	}

	@Override
	public Product createProduct(ProductDTO productDTO) {

		if (productRepository.existsByName(productDTO.getName())) {

			throw new DataIntegrityViolationException("Product with this name already exists");
		}

		Product product = new Product();

		product.setName(productDTO.getName());

		product.setPrice(productDTO.getPrice());

		product.setDescription(productDTO.getDescription());

		Product saved = productRepository.save(product);

		inventoryClient.createInventory(new InventoryRequest(saved.getProductId().longValue(), 0));

		return saved;
	}

	@Override
	public Product updateProduct(ProductDTO productDTO) {

		Product existingProduct = productRepository.findById(productDTO.getProductId())
				.orElseThrow(() -> new NoSuchElementException("Product not found"));

		existingProduct.setName(productDTO.getName());

		existingProduct.setPrice(productDTO.getPrice());

		existingProduct.setDescription(productDTO.getDescription());

		return productRepository.save(existingProduct);
	}

	@Override
	public void deleteProduct(Integer productId) {

		if (!productRepository.existsById(productId)) {

			throw new NoSuchElementException("Product not found");
		}

		productRepository.deleteById(productId);
	}

	@Override
	public List<Product> saveAllProducts(List<ProductDTO> products) {

		List<Product> productList = products.stream().map(dto -> {

			Product product = new Product();

			product.setName(dto.getName());

			product.setPrice(dto.getPrice());

			product.setDescription(dto.getDescription());

			return product;

		}).collect(Collectors.toList());

		return productRepository.saveAll(productList);
	}
}