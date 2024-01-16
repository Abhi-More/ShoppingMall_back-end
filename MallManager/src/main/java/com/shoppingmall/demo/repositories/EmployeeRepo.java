package com.shoppingmall.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingmall.demo.models.Employee;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, String>{
    public Optional<Employee> findBycontactNo(String contactNo);
    public Optional<Employee> deleteBycontactNo(String contactNo);
}
