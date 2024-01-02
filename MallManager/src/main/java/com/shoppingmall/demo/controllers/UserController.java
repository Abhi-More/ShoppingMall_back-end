package com.shoppingmall.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.demo.models.User;
import com.shoppingmall.demo.services.UserInfoService;

@RestController
public class UserController {
	@Autowired
	private UserInfoService service;
	
	@PostMapping("signup")
	public String addUser(User user)
	{
		return service.addUser(user);
	}
	
	@GetMapping("userProfile/{id}")
	public Optional<User> getUser(@PathVariable("id") int id)
	{
		return service.getUserById(id);
	}
}
