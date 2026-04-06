package com.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authservice.dto.*;
import com.authservice.model.*;
import com.authservice.repository.*;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User register(UserRegistrationDTO userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setMobile(userDto.getMobile());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("ROLE_USER"); 
        user.setProvider("LOCAL");
        return repository.save(user);
    }
    
    public User login(String email, String password) {
        // Use IllegalArgumentException for bad credentials to catch in GlobalExceptionHandler
        User user = repository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid Password");
        }
        return user;
    }
}