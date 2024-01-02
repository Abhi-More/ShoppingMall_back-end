package com.shoppingmall.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingmall.demo.models.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	public List<User> findByEmail(String emal);
}
