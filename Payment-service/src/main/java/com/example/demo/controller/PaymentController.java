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
import com.example.demo.dao.PaymentRepository;
import com.example.demo.dto.BookingDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.model.Payment;
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/payments")
@RestController
public class PaymentController {

	private final PaymentRepository paymentRepository;
	private final BookingClient bookingClient;
	private final CustomerClient customerClient;
	public PaymentController(PaymentRepository paymentRepository, BookingClient bookingClient,
			CustomerClient customerClient) {
		super();
		this.paymentRepository = paymentRepository;
		this.bookingClient = bookingClient;
		this.customerClient = customerClient;
	}
	
	ResponseEntity<BookingDto> bookingDto;
	ResponseEntity<CustomerDto> customerDto;
	@PostMapping
	public String save(@RequestBody Payment payment)
	{
	    try
	    {
	        bookingDto = bookingClient.findById(payment.getBookingid());

	        if (bookingDto == null || bookingDto.getBody() == null)
	        {
	            return "No Booking Available";
	        }
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	        return "No Booking Available";
	    }

	    try
	    {
	        customerDto = customerClient.findById(payment.getCustomerid());

	        if (customerDto == null || customerDto.getBody() == null)
	        {
	            return "No Customer Available";
	        }
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	        return "No Customer Available";
	    }

	    paymentRepository.save(payment);

	    return "Payment successfull";
	}	
	
	@GetMapping("/bookings/{bookingid}")
	public ResponseEntity<Payment> findByBookingId(
	        @PathVariable("bookingid") int bookingid) {

	    List<Payment> payments =
	            (List<Payment>) paymentRepository.findByBookingid(bookingid);

	    if (payments.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    for (Payment payment : payments) {

	        if ("PAID".equalsIgnoreCase(payment.getStatus())) {
	            return ResponseEntity.ok(payment);
	        }
	    }

	    return ResponseEntity.notFound().build();
	}
	@GetMapping
	public List<Payment> findAll()
	{
	return paymentRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Payment> findById(@PathVariable("id") int id)
    {
		Payment p=paymentRepository.findById(id).orElse(null);
		if(p==null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(p);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Payment> delete(@PathVariable("id") int id)
	{
		Payment p=paymentRepository.findById(id).orElse(null);
		if(p==null)
		return ResponseEntity.notFound().build();
		paymentRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Payment> update(@PathVariable("id") int id,@RequestBody Payment payment)
	{
		Payment p=paymentRepository.findById(id).orElse(null);
		if(p==null)
			return ResponseEntity.notFound().build();
		p.setAmount(payment.getAmount());
		p.setBookingid(payment.getBookingid());
		p.setCustomerid(payment.getCustomerid());
		p.setPaymentdate(payment.getPaymentdate());
		p.setStatus(payment.getStatus());
		paymentRepository.save(p);
		return ResponseEntity.ok(p);
	}
	
	
	
}
