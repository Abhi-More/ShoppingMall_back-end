package com.shoppingmall.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingmall.demo.models.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

}
