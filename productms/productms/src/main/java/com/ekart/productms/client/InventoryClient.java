package com.ekart.productms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ekart.productms.Dto.InventoryRequest;

@FeignClient(name = "inventoryms")
public interface InventoryClient {

	@PostMapping("/inventory/create")
	void createInventory(@RequestBody InventoryRequest request);
}