package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.DriverDto;

@FeignClient(name="driver-service")
public interface DriverClient {

	@GetMapping("/drivers/{id}")
	public ResponseEntity<DriverDto> findById(@PathVariable("id") int id);
}
