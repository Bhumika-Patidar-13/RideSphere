package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.BookingDto;

@FeignClient(name="booking-service")
public interface BookingClient {

	@GetMapping("/bookings/{id}")
	public ResponseEntity<BookingDto> findById(@PathVariable("id") int id);
}
