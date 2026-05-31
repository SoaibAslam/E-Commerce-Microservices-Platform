package com.ekart.productms.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductDTO {

	private Integer productId;

	@NotBlank(message = "Product name is required")
	private String name;

	@NotNull(message = "Price is required")
	@Positive(message = "Price must be greater than zero")
	private Double price;

	@NotBlank(message = "Discription name is required")
	private String discription;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}
}
