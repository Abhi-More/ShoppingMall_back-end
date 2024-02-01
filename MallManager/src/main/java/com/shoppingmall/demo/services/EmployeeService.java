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

		try {
			if (repo.findByemail(employee.getEmail()).isPresent()) {
				return new ResponseEntity<>("User already exists with same email", HttpStatus.CONFLICT);
			}

			// If contactNo is unique, save the employee
			repo.save(employee);
			return new ResponseEntity<>("Employee added successfully", HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	public List<Employee> getAllEmployee()
	{
		return repo.findAll();
	}

	public ResponseEntity<Employee> getEmployeeById(Integer id)
	{
		Optional<Employee> existingEmployee = repo.findById(id);

		if(existingEmployee.isPresent()){
			return new ResponseEntity<>(existingEmployee.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@Transactional
	public ResponseEntity<Employee> deleteEmployee(Integer id)
	{
		Optional<Employee> existingEmployee = repo.findById(id);

		if(existingEmployee.isPresent()){
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Employee> updateEmployee(Integer id, Employee emp) {
		Optional<Employee> existingEmployee = repo.findById(id);

		if (existingEmployee.isPresent()) {
			Employee newEmp = existingEmployee.get();

			if(emp.getName().length() > 0){
				newEmp.setName(emp.getName());
			}
			if(emp.getEmail().length() > 0){
				newEmp.setEmail(emp.getEmail());
			}
			if(emp.getContactNo().length() > 0){
				newEmp.setContactNo(emp.getContactNo());
			}
			if(emp.getAddress().length() > 0){
				newEmp.setAddress(emp.getAddress());
			}
//			newEmp.setEmpId(emp.getEmpId());
//			newEmp.setContactNo(emp.getContactNo());
//			newEmp.setDateOfJoining(emp.getDateOfJoining());
//			newEmp.setSalary(emp.getSalary());
//			newEmp.setAddress(emp.getAddress());
//			newEmp.setDesignation(emp.getDesignation());
			repo.save(newEmp);

			return new ResponseEntity<>(newEmp, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


}
