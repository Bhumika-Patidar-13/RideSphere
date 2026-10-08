package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.model.Customer;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/customers")
@RestController
public class CustomerController {

	private final CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	@GetMapping
	public List<Customer> findAll()
	{
		return customerRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable("id") int id)
	{
		Customer c=customerRepository.findById(id).orElse(null);
		if(c==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(c);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> deleteById(@PathVariable("id") int id)
	{
		Customer c=customerRepository.findById(id).orElse(null);
		if(c==null)
			return ResponseEntity.notFound().build();
		customerRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public Customer save(@RequestBody Customer c)
	{
		return customerRepository.save(c);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable("id") int id,@RequestBody Customer c)
	{
		Customer c1=customerRepository.findById(id).orElse(null);
		if(c1==null)
			return ResponseEntity.notFound().build();
		c1.setEmail(c.getEmail());
		c1.setMobile(c.getMobile());
		c1.setName(c.getName());
		customerRepository.save(c1);
		return ResponseEntity.ok(c1);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Customer> login(@RequestBody Customer c) {

	    Customer customer =
	            customerRepository.findByEmailAndMobile(c.getEmail(), c.getMobile());

	    if (customer == null) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok(customer);
	}
}
