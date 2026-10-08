package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.AdminRepository;
import com.example.demo.model.Admins;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/admins")
@RestController
public class AdminController {

    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @PostMapping
    public Admins save(@RequestBody Admins admin) {
        return adminRepository.save(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<Admins> login(@RequestBody Admins admin) {

        Admins a = adminRepository.findByUsernameAndPassword(
                admin.getUsername(),
                admin.getPassword()
        );

        if (a == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(a);
    }
}