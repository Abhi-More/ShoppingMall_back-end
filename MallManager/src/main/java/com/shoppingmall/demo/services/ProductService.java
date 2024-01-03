package com.shoppingmall.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.demo.models.Product;
import com.shoppingmall.demo.repositories.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo repo;
	
	public String addProduct(Product product)
	{
		Optional<Product> prod = repo.findById(product.getId());
		if(prod.isPresent())
		{
			return "Product with same id exist";
		}
		
		repo.save(product);
		return "Product Added Successfully.";
	}
	
	public List<Product> getAllProduct() 
	{
		return repo.findAll();
	}
	 	 
	public Product getProduct(Integer id) 
	{
		return repo.findById(id).get();
	}
	 
	public String deleteProduct(Integer id) 
	{
		Optional<Product> prod = repo.findById(id);
		if(prod.isPresent())
		{
			repo.deleteById(id);
			return "Product Deleted";
		}
		return "Product Not Found";
	}
	
	public List<Product> getByCateory(String category)
	{
		return repo.findByCategory(category);
	}
}
