package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Customer;
import com.example.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/api")
public class CustomerController{
	
	@Autowired
	CustomerRepository repo;
	
	private Customer customer1 = new Customer(1L, "bri", "bri.example.com");
	private Customer customer2 = new Customer(2L, "Chris", "chris@example.com");
		
	@GetMapping("/customer/{id}")
	public Optional<Customer> getCustomerById(@PathVariable Long id) {
		return repo.findById(id);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		
	}
	
	@GetMapping
	public 
	
	
}


