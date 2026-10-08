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

import com.example.demo.dao.VehicleRepository;
import com.example.demo.model.Vehicle;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/vehicles")
@RestController
public class VehicleController {

	private final VehicleRepository vehicleRepository;

	public VehicleController(VehicleRepository vehicleRepository) {
		super();
		this.vehicleRepository = vehicleRepository;
	}
	
	@PostMapping
	public Vehicle save(@RequestBody Vehicle v)
	{
		return vehicleRepository.save(v);
	}
	
	@GetMapping
	public List<Vehicle> findAll()
	{
		return vehicleRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vehicle> findById(@PathVariable("id") int id) {

	    Vehicle v = vehicleRepository.findById(id).orElse(null);

	    if (v == null)
	        return ResponseEntity.notFound().build();

	    return ResponseEntity.ok(v);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Vehicle> DeleteById(@PathVariable("id") int id)
	{
		Vehicle v=vehicleRepository.findById(id).orElse(null);
		if(v==null)
			return ResponseEntity.notFound().build();
		vehicleRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Vehicle> updateById(@PathVariable("id") int id,@RequestBody Vehicle v1)
	{
		Vehicle v=vehicleRepository.findById(id).orElse(null);
		if(v==null)
			return ResponseEntity.notFound().build();
		v.setDailyfee(v1.getDailyfee());
		v.setModel(v1.getModel());
		v.setType(v1.getType());
		v.setVehiclename(v1.getVehiclename());
		vehicleRepository.save(v);
		return ResponseEntity.ok(v);
				
	}
}
