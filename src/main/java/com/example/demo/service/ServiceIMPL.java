package com.example.demo.service;


import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.repo.employeeRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceIMPL{
	
	//Exception e= new Exception();
	@Autowired
	private employeeRepo repo; 
	
	//@Autowired
	private EmployeeDto empdto = new EmployeeDto();
	
	// getting the employee on the basis of id
	public EmployeeDto getEmployeeById(int id){
		try {
			Optional<EmployeeEntity> optional = repo.findById(id);
			EmployeeEntity employee = optional.orElseThrow();
			empdto.setId(employee.getId());
			empdto.setName(employee.getName());
			empdto.setPhonenumber(employee.getPhonenumber());
			System.out.println("Employee found successfully");
		}
		catch(Exception e) {
			System.out.println("No Record Found");
			return null;
		}
		
		
		return empdto;
		
	}
	
	//getting all the employee from the DB
	public List<EmployeeDto> getEmployeeList(){
		Iterable<EmployeeEntity> employeeList = repo.findAll();
		List<EmployeeDto> empdtolist = new ArrayList<>();
		
		for (EmployeeEntity e:employeeList) {
			EmployeeDto emp = new EmployeeDto();
			emp.setId(e.getId());
			System.out.println(e.getId());
			emp.setName(e.getName());
			System.out.println(e.getName());
			emp.setPhonenumber(e.getPhonenumber());
			System.out.println(e.getPhonenumber());
			empdtolist.add(emp);
			System.out.println("Adding Employee to this DTO list");
		}
		//empdtolist.add(empdto);
		return empdtolist;
	}
	// inserting the employee in the Db
	//post mapping
	public String addEmployee(EmployeeDto emp) {
		try {
			Optional<EmployeeEntity> optional = repo.findById(emp.getId());
			if(optional.isEmpty()) {
				EmployeeEntity employee = new EmployeeEntity();
				//employee.setId(emp.getId());
				employee.setName(emp.getName());
				employee.setPhonenumber(emp.getPhonenumber());
				repo.save(employee);
			}
			else {
				return "Employee already present in DB";
			}
			
		}
		catch(Exception e) {
			return "Please enter proper data";
		}
			
		return "Employee added successfully";	
	}
	
	
	//deleting any employee depending on the ID
	public String deleteEmployee(int id){
		Optional<EmployeeEntity> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return " please enter a valid ID";	
		}
		else {
			repo.deleteById(id);
			return "Employee deleted successfully";
		}
		
		
	}

	
}
