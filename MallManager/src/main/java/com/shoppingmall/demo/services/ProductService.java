package com.shoppingmall.demo.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.demo.models.Orders;
import com.shoppingmall.demo.models.Product;
import com.shoppingmall.demo.repositories.OrderRepo;
import com.shoppingmall.demo.repositories.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo repo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	public void addProduct(String name, String category, float price, MultipartFile image, String description, Integer discount) throws IOException {
		Product product = new Product();
		product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setImage(image.getBytes());
        product.setDescription(description);
        product.setDiscount(discount);
        repo.save(product);
		
	}
	
	public byte[] getProductImage(Integer id) {
        Product product = repo.findById(id).orElse(null);

        if (product != null) {
            return product.getImage();
        } else {
            return null;
        }
    }
	
	public List<Product> getAllProduct() 
	{
		return repo.findAll();
	}
	 	 
	public Optional<Product> getProduct(Integer id) 
	{
		return repo.findById(id);
	}
	 
	public String deleteProduct(Integer id) 
	{
		Optional<Product> prod = repo.findById(id);
		if(prod.isPresent())
		{
			List<Orders> orderList = orderRepo.findByProductId(id);
			for(Orders o: orderList)
				orderRepo.deleteById(o.getOrderId());
			repo.deleteById(id);
			
			orderRepo.deleteById(id);;
			return "Product Deleted";
		}
		return "Product Not Found";
	}
	
	public List<Product> getByCateory(String category)
	{
		return repo.findByCategory(category);
	}

	public ResponseEntity<Product> updateProduct(Integer id, Product product) throws IOException {
		
		Optional<Product> existingProduct = repo.findById(id);
		if(existingProduct.isPresent())
		{
			Product newProduct = existingProduct.get();
			newProduct.setName(product.getName());
			newProduct.setPrice(product.getPrice());
			newProduct.setDiscount(product.getDiscount());
			repo.save(newProduct);
			return new ResponseEntity<>(newProduct, HttpStatus.OK);
		}		
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
//	public ResponseEntity<Product> updateProduct(Integer id, String name, float price, Integer discount) throws IOException {
//		
//		Optional<Product> existingProduct = repo.findById(id);
//		if(existingProduct.isPresent())
//		{
//			Product newProduct = existingProduct.get();
//			newProduct.setName(name);
//			newProduct.setPrice(price);
//			newProduct.setDiscount(discount);
//			repo.save(newProduct);
//			return new ResponseEntity<>(newProduct, HttpStatus.OK);
//		}		
//		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//	}
}
