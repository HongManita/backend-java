package com.example.demo.model;

public class Employee {

	private Integer employeeId;
	private String firstName;
	private String lastName;
	private Integer sarary;
	
	
	
	
	public Employee() {
		super();
	}
	public Employee(Integer employeeId, String firstName, String lastnameString, Integer sarary, String lastName) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sarary = sarary;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getSarary() {
		return sarary;
	}
	public void setSarary(Integer sarary) {
		this.sarary = sarary;
	}
	
	
}
