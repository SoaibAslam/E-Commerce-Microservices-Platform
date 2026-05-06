package com.ekart.productms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ekart.productms.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	boolean existsByName(String name);

	Optional<Product> findByName(String name);
}
