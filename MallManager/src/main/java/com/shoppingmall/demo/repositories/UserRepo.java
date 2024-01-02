package com.shoppingmall.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingmall.demo.models.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
}
