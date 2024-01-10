package com.shoppingmall.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shoppingmall.demo.models.Shopowner;
import com.shoppingmall.demo.repositories.ShopOwnerRepo;

@Service
public class ShopOwnerService {
	private ShopOwnerRepo repo;
	
	public String addOwner(Shopowner owner)
	{
		repo.save(owner);
		return "Owner Added Successfully";
	}
	
	public List<Shopowner> getAllOwner()
	{
		return repo.findAll();
	}
	
	public ResponseEntity<Shopowner> getOwnerById(Integer id)
	{
		Optional<Shopowner> existingOwner = repo.findById(id);
		
		if(existingOwner.isPresent())
		{
			return new ResponseEntity<>(existingOwner.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<Shopowner> deleteOwner(int id)
	{
		Optional<Shopowner> existingOwner = repo.findById(id);

		if(existingOwner.isPresent()){
			repo.deleteById(id);;
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<Shopowner> updateOwner(int id, Shopowner newOwner)
	{
		Optional<Shopowner> existingOwner = repo.findById(id);

		if(existingOwner.isPresent()){
			repo.save(newOwner);;
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
