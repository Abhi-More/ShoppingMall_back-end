package com.shoppingmall.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.demo.models.Product;
import com.shoppingmall.demo.services.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/{id}")
	public Product getById(@PathVariable("id") Integer id)
	{
		return service.getProduct(id);
	}
	
	@GetMapping("/allproducts/{category}")
	public List<Product> getByCategory(@PathVariable String category)
	{
		return service.getByCateory(category);
	}
	
	@GetMapping("/allproducts")
	public List<Product> getAllProduct()
	{
		return service.getAllProduct();
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id)
	{
		return service.deleteProduct(id);
	}
	
	@PostMapping("addproduct")
	public String addProduct(@RequestBody Product product)
	{
		return service.addProduct(product);
	}
}
