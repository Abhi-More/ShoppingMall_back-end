package com.shoppingmall.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shoppingmall.demo.models.Orders;
import com.shoppingmall.demo.repositories.OrderRepo;

@Service
public class OrderService {
	
	@Autowired
	OrderRepo repo;
	
	public ResponseEntity<Orders> addOrder(Orders order)
	{
		repo.save(order);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	public List<Orders> getAllOrders()
	{
		return repo.findAll();
	}
	
	public List<Orders> getByUserId(Integer id)
	{
		return repo.findByUserId(id);
	}
	
	public ResponseEntity<String> deleteOrder(Integer id)
	{
		Optional<Orders> existingOrder = repo.findById(id);
		
		if(existingOrder.isPresent())
		{
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
