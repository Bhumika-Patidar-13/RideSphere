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

import com.example.demo.client.BookingClient;
import com.example.demo.client.CustomerClient;
import com.example.demo.client.TripClient;
import com.example.demo.dao.FeedbackRepository;
import com.example.demo.dto.BookingDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.TripDto;
import com.example.demo.model.Feedback;

import jakarta.ws.rs.GET;
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/feedbacks")
@RestController
public class FeedbackController {

	private final FeedbackRepository feedbackRepository;
	private final BookingClient bookingClient;
	private final CustomerClient customerClient;
	private final TripClient tripClient;
	public FeedbackController(FeedbackRepository feedbackRepository, BookingClient bookingClient,
			CustomerClient customerClient, TripClient tripClient) {
		super();
		this.feedbackRepository = feedbackRepository;
		this.bookingClient = bookingClient;
		this.customerClient = customerClient;
		this.tripClient = tripClient;
	}
	
	ResponseEntity<BookingDto> bookingDto;
	ResponseEntity<CustomerDto> customerDto;
	ResponseEntity<TripDto> tripDto;
	
	@PostMapping
	public String save(@RequestBody Feedback feedback)
	{
		try
		{
		bookingDto=bookingClient.findById(feedback.getBookingid());
		}
		catch(Exception e1)
		{
			return "Booking ID missing";
		}
		try
		{
		customerDto=customerClient.findById(feedback.getCustomerid());
		}
		catch(Exception e2)
		{
			return "Customer ID missing";
		}
		try
		{
		tripDto=tripClient.findById(feedback.getTripid());
		}
		catch(Exception e3)
		{
			return "Trip ID missing";
		}
		
		feedbackRepository.save(feedback);
		return "feedback submitted";
	}
	
	
	@GetMapping
	public List<Feedback> findAll()
	{
		return feedbackRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Feedback> findById(@PathVariable("id") int id)
	{
		Feedback f=feedbackRepository.findById(id).orElse(null);
		if(f==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(f);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Feedback> deleteById(@PathVariable("id") int id)
	{
		Feedback f=feedbackRepository.findById(id).orElse(null);
		if(f==null)
			return ResponseEntity.notFound().build();
		feedbackRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Feedback> update(@PathVariable("id")int id,@RequestBody Feedback feedback)
	{
		Feedback f=feedbackRepository.findById(id).orElse(null);
		if(f==null)
			return ResponseEntity.notFound().build();
		f.setBookingid(feedback.getBookingid());
		f.setComments(feedback.getComments());
		f.setCustomerid(feedback.getCustomerid());
		f.setFeedbackdate(feedback.getFeedbackdate());
		f.setRatingscore(feedback.getRatingscore());
		f.setTripid(feedback.getTripid());
		feedbackRepository.save(f);
		return ResponseEntity.ok(f);
	}
}  
