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

import com.example.demo.client.DriverClient;
import com.example.demo.client.VehicleClient;
import com.example.demo.dao.TripDao;
import com.example.demo.dto.DriverDto;
import com.example.demo.dto.VehicleDto;
import com.example.demo.model.Trip;
@CrossOrigin(origins = "http://localhost:8080")

@RequestMapping("/trips")
@RestController
public class TripController {

	private final TripDao tripDao;
	private final VehicleClient vehicleClient;
	private final DriverClient driverClient;
	public TripController(TripDao tripDao, VehicleClient vehicleClient, DriverClient driverClient) {
		super();
		this.tripDao = tripDao;
		this.vehicleClient = vehicleClient;
		this.driverClient = driverClient;
	}
	
	ResponseEntity<VehicleDto> vehicleDto;
	ResponseEntity<DriverDto> driverDto;
	
	@PostMapping
	public String save(@RequestBody Trip trip)
	{
		try
		{
		vehicleDto=vehicleClient.findById(trip.getVehicleid());
		}
		catch(Exception e)
		{
			return "Vehicle not found";
		}
		try
		{
		driverDto=driverClient.findById(trip.getDriverid());
		}
		catch(Exception e2)
		{
			return "driver not found";
		}
		tripDao.save(trip);
		
		
		return "Trip Registered";
		
	}
	
	
	@GetMapping
	public List<Trip> findAll()
	{
		return tripDao.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Trip> findById(@PathVariable("id") int id)
	{
		Trip t=tripDao.findById(id).orElse(null);
		if(t==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(t);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Trip> deleteById(@PathVariable("id") int id)
	{
		Trip t=tripDao.findById(id).orElse(null);
		if(t==null)
			return ResponseEntity.notFound().build();
		tripDao.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Trip> update(@PathVariable("id") int id,@RequestBody Trip trip)
	{
		Trip t=tripDao.findById(id).orElse(null);
		if(t==null)
			return ResponseEntity.notFound().build();
		t.setDriverid(trip.getDriverid());
		t.setDuedate(trip.getDuedate());
		t.setRoutedetails(trip.getRoutedetails());
		t.setTitle(trip.getTitle());
		t.setVehicleid(trip.getVehicleid());
		tripDao.save(t);
		return ResponseEntity.ok(t);
	}
	
	
}
