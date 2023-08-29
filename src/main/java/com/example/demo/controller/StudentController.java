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

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepositoty;

	private List<Student> data = new ArrayList<Student>();
	
	
	/*เรียกดูข้อมูลนักศึกษาทั้งหมด*/
	@GetMapping("/students")
	public ResponseEntity<Object> getStudent(){
		
		try {
			List<Student> students = studentRepositoty.findAll();
			return new ResponseEntity<>(students, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.OK);
		}
	}
	
	/*เพิ่มข้อมูลนักศึกษา*/
	@PostMapping("/students")
	public ResponseEntity<Object> addStudent(@RequestBody Student body){
		
	try {
		Student student =  studentRepositoty.save(body);
		
		return new ResponseEntity<>(student, HttpStatus.CREATED);
		
		} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*แก้ไขข้อมูลนักศึกษา*/
	@PutMapping("students/{id}")
	public  ResponseEntity<Object> updateStudent(@PathVariable Integer id,@RequestBody Student body) {
		
		try {
		Optional<Student> student = studentRepositoty.findById(id);
		
		if(student.isPresent()) {
			Student studentEdit = student.get();
			studentEdit.setFirstName(body.getFirstName());
			studentEdit.setLastName(body.getLastName());
			studentEdit.setEmail(body.getEmail());
			
			studentRepositoty.save(student.get());
			return new ResponseEntity<>(student, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>("Student not found",HttpStatus.BAD_REQUEST);
		}}
		catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/*ลบข้อมูลนักศึกษา*/
	@DeleteMapping("students/{id}")
	public ResponseEntity<Object> deleteStudent (@PathVariable Integer id) {
		
		try {
		Optional<Student> student = studentRepositoty.findById(id);
		
		if(student.isPresent()) {
			studentRepositoty.delete(student.get());
			return new ResponseEntity<> ("Delete successful",HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("student not found", HttpStatus.BAD_REQUEST);
		}
	}catch (Exception e) {
		return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	
}
