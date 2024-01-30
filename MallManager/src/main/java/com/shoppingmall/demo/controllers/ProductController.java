package com.shoppingmall.demo.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.demo.models.Product;
import com.shoppingmall.demo.services.ProductService;


@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/{id}")
	public Optional<Product> getById(@PathVariable("id") Integer id)
	{
		return service.getProduct(id);
	}
	
	@GetMapping("/{id}/image")
	public ResponseEntity<?> getProductImage(@PathVariable Integer id) {
		byte[] image = service.getProductImage(id);
		
		if (image != null) {
			return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(image);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/allproducts/{category}")
	public List<Product> getByCategory(@PathVariable String category)
	{
		return service.getByCateory(category);
	}
	
	@GetMapping("/allproducts")
	public List<Product> getAllProduct()
	{
		return service.getAllProduct();
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id)
	{
		return service.deleteProduct(id);
	}
	
	@PostMapping("addproduct")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> addProduct(@RequestParam("name") String name, @RequestParam("category") String category, 
								@RequestParam("price") float price, @RequestParam("image") MultipartFile image)
	{
		try 
		{
            service.addProduct(name, category, price, image);
            return ResponseEntity.ok("Product added successfully");
        } 
		catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to add product");
        }
	}
	
}
