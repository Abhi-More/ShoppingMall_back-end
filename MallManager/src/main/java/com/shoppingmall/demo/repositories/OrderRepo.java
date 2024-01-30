package com.shoppingmall.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingmall.demo.models.Orders;

public interface OrderRepo extends JpaRepository<Orders, Integer>{
	public List<Orders> findByUserId(Integer id);
}
