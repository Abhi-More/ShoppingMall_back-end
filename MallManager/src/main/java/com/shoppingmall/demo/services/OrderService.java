package com.shoppingmall.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shoppingmall.demo.OrderProduct;
import com.shoppingmall.demo.models.Orders;
import com.shoppingmall.demo.models.Product;
import com.shoppingmall.demo.repositories.OrderRepo;
import com.shoppingmall.demo.repositories.ProductRepo;

@Service
public class OrderService {
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	public ResponseEntity<Orders> addOrder(Orders order)
	{
		order.setTimeAndDate(new Date().toString());
		orderRepo.save(order);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	public List<OrderProduct> getAllOrders()
	{
		List<Orders> orderList = orderRepo.findAll();
		
		List<OrderProduct> orderProductList = new ArrayList<>();
		
		for(Orders o: orderList)
		{
			Optional<Product> product = productRepo.findById(o.getProductId());
			if(!product.isPresent())
				continue;
			orderProductList.add(new OrderProduct(orderRepo.findById(o.getOrderId()), productRepo.findById(o.getProductId())));
		}
		
		return orderProductList;
	}
	
	public List<OrderProduct> getByUserId(Integer id)
	{
		List<Orders> orderList = orderRepo.findByUserId(id);
		
		List<OrderProduct> orderProductList = new ArrayList<>();
		
		for(Orders o: orderList)
		{
			Optional<Product> product = productRepo.findById(o.getProductId());
			if(!product.isPresent())
				continue;
			orderProductList.add(new OrderProduct(orderRepo.findById(o.getOrderId()), productRepo.findById(o.getProductId())));
		}
		
		return orderProductList;
	}
	
	public List<OrderProduct> getPendingByUserId(Integer id)
	{
		List<Orders> orderList = orderRepo.findByUserId(id);
		
		List<OrderProduct> orderProductList = new ArrayList<>();
		
		for(Orders o: orderList)
		{
			Optional<Product> product = productRepo.findById(o.getProductId());
			if(!product.isPresent() || !o.getStatus().equals("PENDING"))
				continue;
			orderProductList.add(new OrderProduct(orderRepo.findById(o.getOrderId()), productRepo.findById(o.getProductId())));
		}
		
		return orderProductList;
	}
	
	public List<OrderProduct> getPlacedByUserId(Integer id)
	{
		List<Orders> orderList = orderRepo.findByUserId(id);
		
		List<OrderProduct> orderProductList = new ArrayList<>();
		
		for(Orders o: orderList)
		{
			Optional<Product> product = productRepo.findById(o.getProductId());
			if(!product.isPresent() || o.getStatus().equals("PENDING"))
				continue;
			orderProductList.add(new OrderProduct(orderRepo.findById(o.getOrderId()), productRepo.findById(o.getProductId())));
		}
		
		return orderProductList;
	}
	
	public ResponseEntity<String> deleteOrder(Integer id)
	{
		Optional<Orders> existingOrder = orderRepo.findById(id);
		
		if(existingOrder.isPresent())
		{
			orderRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<String> updateOrder(Integer uid)
	{
		List<Orders> pendingOrders = orderRepo.findByUserId(uid);
		if(pendingOrders.size() > 0)
		{
			for(Orders o: pendingOrders)
			{
				o.setStatus("PLACED");
				o.setTimeAndDate(new Date().toString());
				orderRepo.save(o);
			}
			return new ResponseEntity<>("Orders Placed", HttpStatus.OK);
		}
		return new ResponseEntity<>("Order Not Found", HttpStatus.NOT_FOUND);
	}
	
}
