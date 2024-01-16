package com.shoppingmall.demo.services;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
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

	public ResponseEntity<String> addEmployee(Employee employee) {

		if (repo.findBycontactNo(employee.getContactNo()).isPresent()) {
			return new ResponseEntity<>("ContactNo already exists", HttpStatus.CONFLICT);
		}

		// If contactNo is unique, save the employee
		repo.save(employee);
		return new ResponseEntity<>("Employee added successfully", HttpStatus.CREATED);
	}

	public List<Employee> getAllEmployee()
	{
		return repo.findAll();
	}

	public ResponseEntity<Employee> getEmployeeById(String id)
	{
		Optional<Employee> existingEmployee = repo.findBycontactNo(id);

		if(existingEmployee.isPresent()){
			return new ResponseEntity<>(existingEmployee.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@Transactional
	public ResponseEntity<Employee> deleteEmployee(String id)
	{
		Optional<Employee> existingEmployee = repo.findBycontactNo(id);

		if(existingEmployee.isPresent()){
			repo.deleteBycontactNo(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Employee> updateEmployee(String id, Employee emp) {
		Optional<Employee> existingEmployee = repo.findBycontactNo(id);

		if (existingEmployee.isPresent()) {
			Employee newEmp = existingEmployee.get();
			newEmp.setName(emp.getName());
			newEmp.setContactNo(emp.getContactNo());
			newEmp.setDateOfJoining(emp.getDateOfJoining());
			newEmp.setSalary(emp.getSalary());
			newEmp.setAddress(emp.getAddress());
			newEmp.setDesignation(emp.getDesignation());
			repo.save(newEmp);

			return new ResponseEntity<>(newEmp, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


}
