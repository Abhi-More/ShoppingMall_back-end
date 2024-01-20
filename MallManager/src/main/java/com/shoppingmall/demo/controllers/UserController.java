package com.shoppingmall.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shoppingmall.demo.models.User;
import com.shoppingmall.demo.services.UserInfoService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class UserController {
	@Autowired
	private UserInfoService service;
	
	@PostMapping("/signup")
	public String addUser(@RequestBody User user)
	{		
		return service.addUser(user);
	}
	
	@GetMapping("/profile/{id}")
	public Optional<User> getUser(@PathVariable("id") int id)
	{
		return service.getUserById(id);
	}
	
	@GetMapping("/allusers")
	public List<User> getAllUsers()
	{
		return service.getAllUsers();
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user){
		return service.updateUser(id,  user);
	}

}
