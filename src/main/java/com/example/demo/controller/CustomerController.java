package com.example.demo.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Customer;
import com.example.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/api")
public class CustomerController{
	
	@Autowired
	CustomerRepository repo;
	
	// get customer by ID
	@GetMapping("/customer/{id}")
	public Optional<Customer> getCustomerById(@PathVariable Long id) {
		return repo.findById(id);
	}
	
	// get all customers
	@GetMapping("/customers")
	public Iterable<Customer> getAllCustomers(){
		return repo.findAll();
	}
	
	// create a new customer
	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer){
		if(newCustomer.getName()== null
				|| newCustomer.getEmail()== null) {
			return ResponseEntity.badRequest().build();
		}
		newCustomer=repo.save(newCustomer);
		
		URI location =
				ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newCustomer.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	// delete a customer by id
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable Long id){
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	// update a customer by ID
	
	// update a customer by name
	
	
	// delete all customers
	@DeleteMapping("/customers")
	public ResponseEntity<?> deleteAllCustomers(){
		repo.deleteAll();
		return ResponseEntity.noContent().build();
	}
	
}


