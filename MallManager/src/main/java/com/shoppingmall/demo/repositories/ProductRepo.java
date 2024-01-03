package com.shoppingmall.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingmall.demo.models.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	public List<Product> findByCategory(String category);
}
