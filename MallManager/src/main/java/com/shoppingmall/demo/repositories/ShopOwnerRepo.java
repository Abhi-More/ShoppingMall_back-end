package com.shoppingmall.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingmall.demo.models.Shopowner;

public interface ShopOwnerRepo extends JpaRepository<Shopowner, Integer>{

}
