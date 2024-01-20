package com.shoppingmall.demo.controllers;

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
import com.shoppingmall.demo.services.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class OrderController {

	@Autowired
	OrderService service;
	
	@PostMapping("/add")
	public ResponseEntity<Orders> addOrder(@RequestBody Orders order)
	{
		return service.addOrder(order);
	}
	
	@GetMapping("/{uid}")
	public List<Orders> getByUserId(@PathVariable Integer uid)
	{
		return service.getByUserId(uid);
	}
	
	@GetMapping("/all")
	public List<Orders> getAllOrders()
	{
		return service.getAllOrders();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Integer id)
	{
		return service.deleteOrder(id);
	}
	
}
