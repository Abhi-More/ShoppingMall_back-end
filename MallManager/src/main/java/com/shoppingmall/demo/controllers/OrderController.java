package com.shoppingmall.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.demo.models.Orders;
import com.shoppingmall.demo.models.Product;
import com.shoppingmall.demo.repositories.ProductRepo;
import com.shoppingmall.demo.services.OrderService;
import com.shoppingmall.demo.services.ProductService;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class OrderController {

	@Autowired
	OrderService service;
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/add")
	public ResponseEntity<Orders> addOrder(@RequestBody Orders order)
	{
		return service.addOrder(order);
	}
	
	@GetMapping("/{uid}")
	public List<Product> getByUserId(@PathVariable Integer uid)
	{
		List<Orders> list = service.getByUserId(uid);
		List<Product> productList = new ArrayList<>();
		
		for(Orders o : list)
		{
			productList.add(productService.getProduct(o.getProductId()).orElse(null));
		}
		
		return productList;
	}
	
	@GetMapping("/all")
	public List<Product> getAllOrders()
	{
		List<Orders> list = service.getAllOrders();
		List<Product> productList = new ArrayList<>();
		
		for(Orders o : list)
		{
			productList.add(productService.getProduct(o.getProductId()).orElse(null));
		}
		
		return productList;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Integer id)
	{
		return service.deleteOrder(id);
	}
	
}
