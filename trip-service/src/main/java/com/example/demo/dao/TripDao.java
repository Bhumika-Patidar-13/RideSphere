package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Trip;

public interface TripDao extends JpaRepository<Trip, Integer> {

}
