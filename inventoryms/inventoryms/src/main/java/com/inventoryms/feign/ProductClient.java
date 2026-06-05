package com.inventoryms.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productms")
public interface ProductClient {

	@GetMapping("/products/{id}")
	Object getProductById(@PathVariable("id") Integer id);
}