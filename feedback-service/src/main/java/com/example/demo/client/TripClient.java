package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.TripDto;

@FeignClient(name="trip-service")
public interface TripClient {

	@GetMapping("/trips/{id}")
	public ResponseEntity<TripDto> findById(@PathVariable("id")int id);
}
