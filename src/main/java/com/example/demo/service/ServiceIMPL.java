package com.example.demo.service;


import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
	
	@Autowired
	private EmployeeDto empdto; //;= new EmployeeDto();
	
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
			EmployeeDto empdto = new EmployeeDto();
			empdto.setId(e.getId());
			System.out.println(e.getId());
			empdto.setName(e.getName());
			System.out.println(e.getName());
			empdto.setPhonenumber(e.getPhonenumber());
			System.out.println(e.getPhonenumber());
			empdtolist.add(empdto);
			System.out.println("Adding Employee to this DTO list");
		}
		//empdtolist.add(empdto);
		return empdtolist;
	}
	// inserting the employee in the Db
	//post mapping
	public String addEmployee(EmployeeDto emp) {
		//checking the range of the phone number
		if(emp.getPhonenumber().length()>10) {
			return "Phone Number - More than 10 digits";
		}
		else if(emp.getPhonenumber().length()<10){
			return "Phone Number - Less than 10 digits";
		}
		try {
			System.out.println("addEmployee -> try block");
			EmployeeEntity employeeEntity = repo.findByPhonenumber(emp.getPhonenumber());
			System.out.println(employeeEntity);
			if(employeeEntity==null) {
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
		catch(IncorrectResultSizeDataAccessException e) {
			return "Employee already present in DB ";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Please enter proper data";
		}
			
		return "Employee added successfully";	
	}
	
	//Find By Name
	public EmployeeDto findByName(String name) {
		
		EmployeeEntity employeeEntity = repo.findByName(name);
		empdto.setId(employeeEntity.getId());
		empdto.setName(employeeEntity.getName());
		empdto.setPhonenumber(employeeEntity.getPhonenumber());
		return empdto;
		
	}
	
	//Update Mobile number
	public String updatePhoneNumber(int id, String newNumber) {
		Optional<EmployeeEntity> employeeEntity = repo.findById(id);
		try {
			if(employeeEntity.isPresent()) {
//				String phonenumber = emp.getPhonenumber();
				EmployeeEntity ee= employeeEntity.orElseThrow();
				//ee.setName(employeeEntity.orElseThrow().getName());
				System.out.println("Updating phone number...............");
				ee.setPhonenumber(newNumber);
				repo.save(ee);
				
				System.out.println("Phone number modified with new number "+ newNumber);
				
			}
			else {
				return "Employee is not present is DB";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Some exception happened";
		}
		return "Phone number updated successfully";
		
	}
	
	//deleting any employee depending on the ID
	public String deleteEmployee(int id){
		Optional<EmployeeEntity> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;	
		}
		else {
			repo.deleteById(id);
			return "Employee deleted successfully";
		}
		
		
	}

	
}
