package com.example.demo.client;

import java.net.http.HttpResponse.ResponseInfo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.VehicleDto;

@FeignClient(name="vehicle-service")
public interface VehicleClient {

	@GetMapping("/vehicles/{id}")
	public ResponseEntity<VehicleDto> findById(@PathVariable("id") int id);
}
