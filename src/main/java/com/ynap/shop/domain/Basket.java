package com.ynap.shop.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
	Map<String,Product> products;
	
	public Basket() {
		this.products = new HashMap<>();
	}
	
	public void addProduct(Product product) {
		this.products.put(product.getId(), product);
	}
	
	//I don't want the list changed without using the addProduct method
	public List<Product> getProducts(){
		return List.copyOf(this.products.values());
	}
	
	public void removeProduct(String id) {
		products.remove(id);
	}
	
	public BigDecimal getTotal(){
		return products.values().stream().map(p -> p.getPrice())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
