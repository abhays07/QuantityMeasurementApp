package com.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
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
            // FIXED: Ensure extracted user data is valid and token is not expired
            String email = jwtUtil.extractEmail(token); 
            boolean isValid = (email != null && !jwtUtil.isTokenExpired(token));
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