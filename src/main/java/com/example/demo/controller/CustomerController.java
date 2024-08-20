package com.example.demo.controller;
import java.util.Arrays;
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
@RequestMapping("/customers")
public class CustomerController{
	
	private List<Customer> customers = Arrays.asList(
			new Customer(1L, "bri", "bri.example.com"), new Customer(2L, "Chris", "chris@example.com"));
		
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		for(Customer customer : customers) {
			if(customer.getId().equals(id)) {
				return ResponseEntity.ok(customer);
			}
		}
		return null;
	}
	
	@GetMapping
	public List<Customer>getAllCustomers(){
		return customers;
	}
}


