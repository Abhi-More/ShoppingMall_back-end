package com.shoppingmall.demo.controllers;

import java.util.List;
import java.util.Optional;

import com.shoppingmall.demo.config.UserInfoDetails;
import com.shoppingmall.demo.dto.AuthRequest;
import com.shoppingmall.demo.models.UserInfo;
import com.shoppingmall.demo.repositories.UserInfoRepository;
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
@RequestMapping()
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
	@Autowired
	UserInfoRepository userInfoRepository;


	@GetMapping("/user/{id}")
	@PreAuthorize("isAuthenticated()")
	public Optional<UserInfo> getUser(@PathVariable Integer id){
		return service.getUserById(id);
	}
	
	@GetMapping("/user/all-users")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
	@PreAuthorize("isAuthenticated()")
	public List<UserInfo> getAllUsers()
	{
		return service.getAllUsers();
	}


	@PutMapping("/user/{id}")
	@PreAuthorize("isAuthenticated()")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<UserInfo> updateUser(@PathVariable Integer id, @RequestBody UserInfo user){
		return service.updateUser(id,  user);
	}

	//implementation in UserInfoService
	@PostMapping("/auth/register")
	public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo) {
		return InfoService.addUser(userInfo);
	}



	@PostMapping("/auth/login")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		System.out.println(authentication.isAuthenticated());
		if (authentication.isAuthenticated()) {
			UserInfoDetails userDetails = (UserInfoDetails) authentication.getPrincipal();
			Optional<UserInfo> user = userInfoRepository.findByemail(userDetails.getName());
			return jwtService.generateToken(authRequest.getUsername(), user.get().getId());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}

	@DeleteMapping("user/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id){
		return service.deleteUser(id);
	}

}
