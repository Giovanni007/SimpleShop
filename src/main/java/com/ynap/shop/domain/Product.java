package com.ynap.shop.domain;

import java.math.BigDecimal;

public class Product {
	
	private String id;
	
	private String name;
	
	private BigDecimal price;

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	private void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public Product(String id, String name, BigDecimal price) {
		this.setId(id);
		this.setName(name);
		this.setPrice(price);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}


}
