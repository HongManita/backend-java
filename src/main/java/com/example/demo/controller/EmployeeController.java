package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Employee> getEmployee(){
		return employeeRepositoty.findAll();
	}
	
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee body) {
		
	
		return employeeRepositoty.save(body);
		
	}
	
	@GetMapping("/employee/{employeeId}")
	public Optional<Employee> getEmployeeDetail(@PathVariable Integer employeeId) {
		
		
		Optional<Employee> employee = employeeRepositoty.findById(employeeId);
		
		return employee;
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
	public String deleteEmployee (@PathVariable Integer employeeId) {
		
		Optional<Employee> employee = employeeRepositoty.findById(employeeId);
		
		if(employee.isPresent()) {
			employeeRepositoty.delete(employee.get());
			return "DELETE SUCESS";
		}
		
		else {
		return ("employee not found");
		}
	}
	
}
