package com.shoppingmall.demo.services;

import java.util.Optional;
import java.util.List;

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
		Optional<User> user = repo.findById(id);

		return user == null ? Optional.empty(): user;
	}
	
	public String addUser(User user)
	{
		List<User> userList = repo.findByEmail(user.getEmail());
		if(userList.isEmpty())
		{
			repo.save(user);
			return "User added successfully.";			
		}
			
		return "This email already in use. Use another email.";
	}

	public List<User> getAllUsers() {
		
		return repo.findAll();
	}

}
