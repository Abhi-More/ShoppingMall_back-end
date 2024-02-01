package com.shoppingmall.demo.controllers;

import com.shoppingmall.demo.models.Employee;
import com.shoppingmall.demo.services.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public List<Employee> getAllEmployee(){

        return empService.getAllEmployee();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id){
    	
    	return empService.getEmployeeById(id);
    }


    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> addEmployee(@RequestBody Employee emp){
        return empService.addEmployee(emp);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee emp){
        return empService.updateEmployee(id, emp);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id){
        return empService.deleteEmployee(id);
    }
}
