package com.shoppingmall.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingmall.demo.models.Shop;

public interface ShopRepo extends JpaRepository<Shop, Integer>{

}
