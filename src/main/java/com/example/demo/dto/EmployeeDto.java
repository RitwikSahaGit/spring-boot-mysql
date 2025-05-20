package com.example.demo.dto;


import org.springframework.stereotype.Component;

@Component
public class EmployeeDto {
	
	
	private int id;
	
	private String name;
	
	private String phonenumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

}
