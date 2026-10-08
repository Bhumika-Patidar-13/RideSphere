package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Admins;

public interface AdminRepository extends JpaRepository<Admins, String> {
	  Admins findByUsernameAndPassword(String username, String password);
}
