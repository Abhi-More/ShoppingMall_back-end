package com.shoppingmall.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.demo.OrderProduct;
import com.shoppingmall.demo.models.Orders;
import com.shoppingmall.demo.services.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@PostMapping("/add")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Orders> addOrder(@RequestBody Orders order)
	{
		return orderService.addOrder(order);
	}
	
	@GetMapping("/{uid}")
	@PreAuthorize("isAuthenticated()")
	public List<OrderProduct> getByUserId(@PathVariable Integer uid)
	{		
		return orderService.getByUserId(uid);
	}
	
	@GetMapping("/{uid}/pending")
	@PreAuthorize("isAuthenticated()")
	public List<OrderProduct> getPendingByUserId(@PathVariable Integer uid)
	{		
		return orderService.getPendingByUserId(uid);
	}
	
	@GetMapping("/{uid}/placed")
	@PreAuthorize("isAuthenticated()")
	public List<OrderProduct> getPlacedByUserId(@PathVariable Integer uid)
	{		
		return orderService.getPlacedByUserId(uid);
	}
	
	@GetMapping("/all")
	@PreAuthorize("isAuthenticated()")
	public List<OrderProduct> getAllOrders()
	{		
		return orderService.getAllOrders();
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deleteOrder(@PathVariable Integer id)
	{
		return orderService.deleteOrder(id);
	}
	
	@PutMapping("/{uid}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> updateStatus(@PathVariable Integer uid)
	{
		return orderService.updateOrder(uid);
	}
	
}
