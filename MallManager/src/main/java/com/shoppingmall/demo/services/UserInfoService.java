package com.shoppingmall.demo.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	public ResponseEntity<User> updateUser(Integer id, User user) {
		Optional<User> existingUser = repo.findById(id);

		if(existingUser.isPresent()){
			User newUser = existingUser.get();
			if(user.getEmail() != null){
				newUser.setEmail(user.getEmail());
			}
			if(user.getPassword() != null){
				newUser.setPassword(user.getPassword());
			}
			repo.save(newUser);
			return new ResponseEntity<>(newUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<User> deleteUser(Integer id)
	{
		Optional<User> existingUser = repo.findById(id);
		
		if(existingUser.isPresent())
		{
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 	}
}
