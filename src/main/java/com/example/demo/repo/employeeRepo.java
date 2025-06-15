package com.example.demo.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmployeeEntity;

@Repository
public interface employeeRepo extends CrudRepository<EmployeeEntity, Integer> {

	public EmployeeEntity findByName(String name);
	
	public EmployeeEntity findByPhonenumber(String phonenumber);
	
	
}
