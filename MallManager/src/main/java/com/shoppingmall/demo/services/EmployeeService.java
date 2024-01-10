package com.shoppingmall.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shoppingmall.demo.models.Employee;
import com.shoppingmall.demo.repositories.EmployeeRepo;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepo repo;
	
	public String addEmployee(Employee employee)
	{
		repo.save(employee);
		return "Employee Added Successfully";
	}
	
	public List<Employee> getAllEmployee()
	{
		return repo.findAll();
	}
	
	public ResponseEntity<Employee> getEmployeeById(int id)
	{
		Optional<Employee> existingEmployee = repo.findById(id);
		
		if(existingEmployee.isPresent()){
			return new ResponseEntity<>(existingEmployee.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<Employee> deleteEmployee(int id)
	{
		Optional<Employee> existingEmployee = repo.findById(id);

		if(existingEmployee.isPresent()){
			repo.deleteById(id);;
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<Employee> updateEmployee(int id, Employee emp)
	{
		Optional<Employee> existingEmployee = repo.findById(id);

		if(existingEmployee.isPresent()){
			repo.save(emp);
			return new ResponseEntity<>(emp, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
