package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDto;
//import com.example.demo.service.Service;
import com.example.demo.service.ServiceIMPL;

@RestController
@RequestMapping("/home")
public class ApiController {
	
	@Autowired
	ServiceIMPL employeeService;
	
	@GetMapping("/test")
	public String responseTest() {
		return "Congratulation! API is working.";
	}
//	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDto>> getEmployeeList() {
		
		return new ResponseEntity<>(employeeService.getEmployeeList(), HttpStatus.OK);
	}
	
	@GetMapping("/employees/{id}")
	//public EmployeeDto getEmployeeDetails(@PathVariable int id) 
	public ResponseEntity<EmployeeDto> getEmployeeDetails(@PathVariable int id) 
	{
		
		EmployeeDto e =employeeService.getEmployeeById(id);
		return new ResponseEntity<>(e, HttpStatus.FOUND);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employee) {
		
		
		return new ResponseEntity<> (employeeService.addEmployee(employee), HttpStatus.CREATED);
	}
//	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
		
		//return employeeService.deleteEmployee(id);
		String str = employeeService.deleteEmployee(id);
		System.out.println(str);
		if(str==null) {
			return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND );
		}
		else {
			return new ResponseEntity<>("Deleted record", HttpStatus.OK );
		}
			
	}
//	
//	@PutMapping("/employees")
//	public String editEmployee(@RequestBody EmployeeDto employee) {
//		System.out.println("Inside API put method");
//		return employeeService.editEmployee(employee);
//		//return null;
//	}
}
