package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.EmployeeDto;

@Component
public interface Service {
	
	public EmployeeDto getEmployeeById(int id);
	
	public List<EmployeeDto> getEmployeeList();
	
	

}
