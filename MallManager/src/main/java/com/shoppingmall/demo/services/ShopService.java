package com.shoppingmall.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shoppingmall.demo.models.Shop;
import com.shoppingmall.demo.repositories.ShopRepo;

@Service
public class ShopService {
	@Autowired
	private ShopRepo repo;
	
	public List<Shop> getAllShops()
	{
		return repo.findAll();
	}
	
	public ResponseEntity<Shop> getShopById(Integer id)
	{
		Optional<Shop> existingShop = repo.findById(id);
		if(existingShop.isPresent())
		{
			return new ResponseEntity<>(existingShop.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	public String addShop(Shop shop)
	{
		repo.save(shop);
		return "Shop Added Successfully.";
	}
	
	public ResponseEntity<Shop> deleteShop(Integer id)
	{
		Optional<Shop> existingShop = repo.findById(id);
		if(existingShop.isPresent())
		{
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<Shop> updateShop(Integer id, Shop newShop)
	{
		Optional<Shop> existingShop = repo.findById(id);
		if(existingShop.isPresent())
		{
			repo.save(newShop);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
