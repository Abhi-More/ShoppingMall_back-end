package com.shoppingmall.demo;

import java.util.Optional;

import com.shoppingmall.demo.models.Orders;
import com.shoppingmall.demo.models.Product;

public class OrderProduct {
	private Optional<Orders> order;
	private Optional<Product> product;
	
	public Optional<Product> getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = Optional.ofNullable(product);
	}
	public OrderProduct(Optional<Orders> order, Optional<Product> optional) {
		super();
		this.order = order;
		this.product = optional;
	}
	
	public OrderProduct() {
		super();
	}
	public Optional<Orders> getOrder() {
		return order;
	}
	public void setOrder(Optional<Orders> order) {
		this.order = order;
	}
	
}
