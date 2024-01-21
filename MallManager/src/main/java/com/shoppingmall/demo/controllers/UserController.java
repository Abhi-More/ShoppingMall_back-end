package com.shoppingmall.demo.controllers;

import java.util.List;
import java.util.Optional;

import com.shoppingmall.demo.dto.AuthRequest;
import com.shoppingmall.demo.models.UserInfo;
import com.shoppingmall.demo.services.JwtService;
import com.shoppingmall.demo.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.shoppingmall.demo.models.User;
import com.shoppingmall.demo.services.UserService;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class UserController {
	@Autowired
	private UserService service;

	@Autowired
	private UserInfoService InfoService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@PostMapping("/signup")
//	public String addUser(@RequestBody User user)
//	{
//		return service.addUser(user);
//	}
	
	@GetMapping("/profile/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Optional<User> getUser(@PathVariable("id") int id)
	{
		return service.getUserById(id);
	}
	
	@GetMapping("/allusers")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<User> getAllUsers()
	{
		return service.getAllUsers();
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user){
		return service.updateUser(id,  user);
	}

	@PostMapping("/signup")
	public String addNewUser(@RequestBody UserInfo userInfo) {
		return InfoService.addUser(userInfo);
	}

	@GetMapping("/user/userProfile")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String userProfile() {
		return "Welcome to User Profile";
	}

	@GetMapping("/admin/adminProfile")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String adminProfile() {
		return "Welcome to Admin Profile";
	}

	@PostMapping("/generateToken")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		System.out.println(authentication.isAuthenticated());
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}



}
