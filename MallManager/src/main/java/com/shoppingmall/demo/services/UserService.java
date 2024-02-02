package com.shoppingmall.demo.services;

import java.util.Optional;
import java.util.List;

import com.shoppingmall.demo.models.UserInfo;
import com.shoppingmall.demo.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shoppingmall.demo.models.User;


@Service
public class UserService {
	
	@Autowired
	private UserInfoRepository repo;
	@Autowired
	private PasswordEncoder encoder;
	
	public Optional<UserInfo> getUserById(int id)
	{
		return repo.findById(id);
	}


	public List<UserInfo> getAllUsers() {
		
		return repo.findAll();
	}

	public ResponseEntity<UserInfo> updateUser(Integer id, UserInfo user) {
		Optional<UserInfo> existingUser = repo.findById(id);

		if(existingUser.isPresent()){
			UserInfo newUser = existingUser.get();
			if(user.getEmail() != null && !user.getEmail().isEmpty()){
				newUser.setEmail(user.getEmail());
			}
			if(user.getName() != null && !user.getName().isEmpty()){
				newUser.setName(user.getName());
			}
			if(user.getAddress() != null && !user.getAddress().isEmpty()){
				newUser.setAddress(user.getAddress());
			}
			if(user.getContactNo() != null && !user.getContactNo().isEmpty()){
				newUser.setContactNo(user.getContactNo());
			}
			repo.save(newUser);
			return new ResponseEntity<>(newUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<User> deleteUser(Integer id)
	{
		Optional<UserInfo> existingUser = repo.findById(id);
		
		if(existingUser.isPresent())
		{
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 	}
}
