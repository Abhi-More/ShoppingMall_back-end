package com.shoppingmall.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingmall.demo.models.User;
import org.springframework.http.ResponseEntity;

public interface UserRepo extends JpaRepository<User, Integer>{
	public Optional<User> findByemail(String emal);
}
