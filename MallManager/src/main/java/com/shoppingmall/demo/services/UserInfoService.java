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
	
	public Optional<User> getUserById(int id)
	{
		Optional<User> userDetail = repo.findById(id);

		return userDetail == null ? null: userDetail;
	}
	
	public String addUser(User user)
	{
		repo.save(user);
		return "User added successfully.";
	}
}
