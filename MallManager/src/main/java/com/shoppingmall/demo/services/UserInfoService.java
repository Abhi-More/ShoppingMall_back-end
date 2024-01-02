package com.shoppingmall.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.demo.models.User;
import com.shoppingmall.demo.repositories.UserRepo;

@Service
public class UserInfoService {
	
	@Autowired
	private UserRepo repo;
	
	public Optional<User> getUserInfo(int id)
	{
		Optional<User> userDetail = Optional.of(repo.findById(id).orElse(null));

		return userDetail;
	}
	
	public String addUser(User user)
	{
		repo.save(user);
		return "User added successfully.";
	}
}
