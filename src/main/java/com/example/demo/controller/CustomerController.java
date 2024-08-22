package com.example.demo.controller;
import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	// update or create a customer by email / no content
	@PutMapping("/customer/{id}")
	public ResponseEntity<?> createOrUpdateCustomer(@PathVariable Long id,
			@RequestBody Customer customerDetails){
		// checks to see if the customer has all the required details
		if(customerDetails.getName() == null ||
				customerDetails.getEmail() == null || customerDetails.getPassword() == null) {
			return ResponseEntity
					.badRequest()
					.build();
		}
		customerDetails = repo.save(customerDetails);
		return ResponseEntity.ok().build();
		
		//checks to see if the customer exists by its ID
		// if they don't exist, they will be created.
		///if(!repo.existsById(id))
		//{
		//	customerDetails = repo.save(customerDetails);
		//	return ResponseEntity.ok().build();
		//}
		//return null;
		
	}
	
	
	// delete a customer by id
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable Long id){
		if(repo.existsById(id) ) {
			repo.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	// delete all customers
	@DeleteMapping("/customers")
	public ResponseEntity<?> deleteAllCustomers(){
		repo.deleteAll();
		return ResponseEntity.noContent().build();
	}
	
}


