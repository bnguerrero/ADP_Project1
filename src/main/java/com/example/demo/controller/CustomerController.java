package com.example.demo.controller;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Customer;

@RestController
@RequestMapping("/api")
public class CustomerController{
	
	ArrayList<Customer> customers = new ArrayList<Customer>();
	
	public CustomerController() {
		customers.add(new Customer(1L, "bri", "myPassword", "bri.example.com"));
		customers.add(new Customer(2L, "Chris", "hisPassword", "chris@example.com"));
	}
		
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		for(Customer customer : customers) {
			if(customer.getId().equals(id)) {
				return customer;
			}
		}
		return null;
	}
	
	@GetMapping("/customers")
	public ArrayList<Customer>getAllCustomers(){
		return customers;
	}
}


