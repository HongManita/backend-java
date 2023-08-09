package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepositoty;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepositoty employeeRepositoty;

	
	
	private List<Employee> data = new ArrayList<Employee>();
	
	
	@GetMapping("/employee")
	public ResponseEntity<Object> getEmployee(){
		
		try {
			List<Employee> employees = employeeRepositoty.findAll();
			return new ResponseEntity<>(employees, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.OK);
		}
	}

	
	@PostMapping("/employee")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee body){
		
	try {
		Employee employee = employeeRepositoty.save(body);
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	    }catch (Exception e) {
		return new ResponseEntity<>("Internal server error",HttpStatus.CREATED);
		
	}
	}
	
	
	
	
	@GetMapping("/employee/{employeeId}")
	public  ResponseEntity<Object> getEmployeeDetail(@PathVariable Integer employeeId) {
		
		
		try {
			Optional<Employee> employee = employeeRepositoty.findById(employeeId);
					if(employee.isPresent()) {
						return new ResponseEntity<>(employee, HttpStatus.OK);
					}else {
						return new ResponseEntity<>("Employee not found",HttpStatus.BAD_REQUEST);
					}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	@PutMapping("employee/{employeeId}")
	public Employee updateEmployee(@PathVariable Integer employeeId,@RequestBody Employee body) {
		
		Optional<Employee> employee = employeeRepositoty.findById(employeeId);
		
		if(employee.isPresent()) {
			Employee employeeEdit = employee.get();
			employeeEdit.setFirstName(body.getFirstName());
			employeeEdit.setLastName(body.getLastName());
			employeeEdit.setSarary(body.getSarary());
			employeeEdit.setEmployeeId(body.getEmployeeId());
			
			employeeRepositoty.save(employee.get());
			
			return employee.get();	
		}else {
			return null ;
		}
	}
	
		
		
	
	
	@DeleteMapping("employee/{employeeId}")
	public ResponseEntity<Object> deleteEmployee (@PathVariable Integer employeeId) {
		
		try {
		Optional<Employee> employee = employeeRepositoty.findById(employeeId);
		
		if(employee.isPresent()) {
			employeeRepositoty.delete(employee.get());
			return new ResponseEntity<> ("Delete SUCESS",HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("employee not found", HttpStatus.BAD_REQUEST);
		}
	}catch (Exception e) {
		return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}}
