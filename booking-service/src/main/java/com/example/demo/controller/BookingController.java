
package com.example.demo.controller;

import com.example.demo.client.CustomerClient;
import com.example.demo.client.DriverClient;
import com.example.demo.client.VehicleClient;

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

import com.example.demo.dao.BookingRepository;
import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.DriverDto;
import com.example.demo.dto.VehicleDto;
import com.example.demo.model.Booking;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/bookings")
@RestController
public class BookingController {

    private final BookingRepository bookingRepository;
    private final VehicleClient vehicleClient;
    private final CustomerClient customerClient;
    private final DriverClient driverClient;


    public BookingController(
            BookingRepository bookingRepository,
            VehicleClient vehicleClient,
            CustomerClient customerClient,
            DriverClient driverClient) {

        this.bookingRepository = bookingRepository;
        this.vehicleClient = vehicleClient;
        this.customerClient = customerClient;
        this.driverClient = driverClient;
    }


    @PostMapping
    public String save(@RequestBody Booking b) {

        // -----------------------------
        // CHECK CUSTOMER
        // -----------------------------

        try {

            CustomerDto customer =
                    customerClient.findById(b.getCustomerId());

            if (customer == null) {
                return "Customer not Available";
            }

        } catch (Exception e) {

            return "Customer not Available";
        }


        // -----------------------------
        // CHECK VEHICLE
        // -----------------------------

        try {

            VehicleDto vehicle =
                    vehicleClient.findById(b.getVehicleId());

            if (vehicle == null) {
                return "Vehicle not Available";
            }

        } catch (Exception e) {

            return "Vehicle not Available";
        }


        // -----------------------------
        // DRIVER CHECK
        // -----------------------------

        if (b.isDriverRequired()) {

            // Driver is required but no driver selected
            if (b.getDriverId() == null) {

                return "Please select a driver";
            }


            try {

                DriverDto driver =
                        driverClient.findById(b.getDriverId());

                if (driver == null) {
                    return "Driver not Available";
                }

            } catch (Exception e) {

                return "Driver not Available";
            }

        } else {

            // Customer doesn't want a driver
            b.setDriverId(null);
        }


        // -----------------------------
        // SAVE BOOKING
        // -----------------------------

        bookingRepository.save(b);

        return "Booking Successful";
    }


    // -----------------------------
    // FIND BOOKINGS BY CUSTOMER
    // -----------------------------

    @GetMapping("/customer/{customerId}")
    public List<Booking> findByCustomerId(
            @PathVariable("customerId") int customerId) {

        return bookingRepository.findByCustomerId(customerId);
    }


    // -----------------------------
    // FIND ALL BOOKINGS
    // -----------------------------

    @GetMapping
    public List<Booking> findAll() {

        return bookingRepository.findAll();
    }


    // -----------------------------
    // FIND BOOKING BY ID
    // -----------------------------

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(
            @PathVariable("id") int id) {

        Booking b =
                bookingRepository.findById(id).orElse(null);

        if (b == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(b);
    }


    // -----------------------------
    // DELETE BOOKING
    // -----------------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<Booking> delete(
            @PathVariable("id") int id) {

        Booking b =
                bookingRepository.findById(id).orElse(null);

        if (b == null) {
            return ResponseEntity.notFound().build();
        }

        bookingRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    // -----------------------------
    // UPDATE BOOKING
    // -----------------------------

    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(
            @PathVariable("id") int id,
            @RequestBody Booking b) {

        Booking b1 =
                bookingRepository.findById(id).orElse(null);

        if (b1 == null) {
            return ResponseEntity.notFound().build();
        }


        b1.setCustomerId(b.getCustomerId());
        b1.setVehicleId(b.getVehicleId());
        b1.setDate(b.getDate());
        b1.setDurationdays(b.getDurationdays());
        b1.setStatus(b.getStatus());

        b1.setDriverRequired(b.isDriverRequired());
        b1.setDriverId(b.getDriverId());


        if (!b.isDriverRequired()) {
            b1.setDriverId(null);
        }


        bookingRepository.save(b1);

        return ResponseEntity.ok(b1);
    }
}