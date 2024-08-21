package com.example.demo.domain;


public class Customer{

	 Long id;
	 String name;
	 String email;
	 String password;
		
	public Customer(Long id, String name, String password, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
		
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
		
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}