package com.shoppingmall.demo.controllers;


import com.shoppingmall.demo.dto.AuthRequest;
import com.shoppingmall.demo.models.User;
import com.shoppingmall.demo.models.UserInfo;
import com.shoppingmall.demo.repositories.UserInfoRepository;
import com.shoppingmall.demo.repositories.UserRepo;
import com.shoppingmall.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class TempUserController {

    @Autowired
    UserService userService;
    @Autowired
    UserInfoRepository userRepo;
    @PostMapping
    public ResponseEntity<UserInfo> loginUser(@RequestBody AuthRequest authRequest) {
        // Find user by email
        Optional<UserInfo> user = userRepo.findByemail(authRequest.getUsername());

        // Check if user exists and provided password matches
        if (user.isPresent()  && authRequest.getPassword().equals(user.get().getPassword())) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
