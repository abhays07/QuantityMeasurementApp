package com.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
            .csrf(csrf -> csrf.disable()) 
            .cors(cors -> cors.disable()) // Handled by CorsWebFilter Bean
            .authorizeExchange(exchange -> exchange
                .pathMatchers("/auth/**", "/oauth2/**", "/login/**").permitAll()
                .pathMatchers("/api/v1/quantities/**", "/actuator/**").permitAll()
                .anyExchange().permitAll() 
            )
            .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        
        // Allowed Origins (Added 5173 for Vite)
       corsConfig.setAllowedOrigins(Arrays.asList(
            "http://localhost:5173", 
            "http://localhost:3000",
            "http://43.205.228.140.nip.io",
            "http://43.205.228.140.nip.io:8085", 
            "http://43.205.228.140",             
            "http://43.205.228.140:8085"         
            ));
        
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Use wildcard for headers to avoid 403s on customized Axios requests
        corsConfig.setAllowedHeaders(Arrays.asList("*")); 
        
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}