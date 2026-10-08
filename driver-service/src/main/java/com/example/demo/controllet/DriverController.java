package com.example.demo.controllet;

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

import com.example.demo.dao.DriverRepository;
import com.example.demo.model.Driver;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/drivers")
@RestController
public class DriverController {

	private final DriverRepository driverRepository;

	public DriverController(DriverRepository driverRepository) {
		super();
		this.driverRepository = driverRepository;
	}
	
	@GetMapping
	public List<Driver> findAll()
	{
		return driverRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Driver> findById(@PathVariable("id") int id)
	{
		Driver d=driverRepository.findById(id).orElse(null);
		if(d==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(d);
	}
	
	@PostMapping
	public Driver save(@RequestBody Driver d)
	{
		return driverRepository.save(d);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Driver> update(@PathVariable("id") int id,@RequestBody Driver d)
	{
	     Driver d1=	driverRepository.findById(id).orElse(null);
	     if(d1==null)
	    	 return ResponseEntity.notFound().build();
	     d1.setEmail(d.getEmail());
	     d1.setLicenseNumber(d.getLicenseNumber());
	     d1.setName(d.getName());
	     d1.setSpecialization(d.getSpecialization());
	     driverRepository.save(d1);
	     return ResponseEntity.ok(d1);     
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Driver> delete(@PathVariable("id") int id)
	{
		Driver d1=driverRepository.findById(id).orElse(null);
		if(d1==null)
			return ResponseEntity.notFound().build();
		driverRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
