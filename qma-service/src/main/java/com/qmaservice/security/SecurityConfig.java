package com.qmaservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.cors(cors -> cors.disable()) // Handled by Gateway
				.csrf(csrf -> csrf.disable()) // Disabled for Stateless APIs
				
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				
				.authorizeHttpRequests(authorize -> authorize
						// Public paths
						.requestMatchers("/actuator/**").permitAll()
						
						// TEMPORARY: Allow all quantity paths to debug the 403 issue
						// This includes add, subtract, convert, etc.
						.requestMatchers("/api/v1/quantities/**").permitAll()

						.anyRequest().authenticated());

		// Add the filter to ensure we can still extract user ID from the token if provided
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}