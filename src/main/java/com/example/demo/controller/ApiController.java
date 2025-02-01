package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<EmployeeDto> getEmployeeList() {
		
		return employeeService.getEmployeeList();
	}
	
	@GetMapping("/employees/{id}")
	public EmployeeDto getEmployeeDetails(@PathVariable int id) {
		
		return employeeService.getEmployeeById(id);
	}
	
	@PostMapping("/employees")
	public String addEmployee(@RequestBody EmployeeDto employee) {
		
		
		return employeeService.addEmployee(employee);
	}
//	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable int id) {
		
		return employeeService.deleteEmployee(id);
	}
//	
//	@PutMapping("/employees")
//	public String editEmployee(@RequestBody EmployeeDto employee) {
//		System.out.println("Inside API put method");
//		return employeeService.editEmployee(employee);
//		//return null;
//	}
}
