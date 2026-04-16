package com.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

import com.authservice.dto.*;
import com.authservice.model.*;
import com.authservice.security.*;
import com.authservice.service.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        try {
            // Use your existing JwtUtil to check if the token is still valid
        	jwtUtil.extractUsername(token);
            boolean isValid = !jwtUtil.isTokenExpired(token);
            return ResponseEntity.ok(isValid);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

	@PostMapping("/register")
	public User register(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
		return service.register(registrationDTO);
	}
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody AuthRequest request) {
		User user = service.login(request.getEmail(),request.getPassword());
		String token = jwtUtil.generateToken(user);
		
		return new AuthResponse(token);
		
	}
}
