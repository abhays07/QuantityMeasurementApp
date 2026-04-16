package com.authservice.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.authservice.model.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secretkey}")
    private String secretKey;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // For Standard Login (Manual/Email)
    public String generateToken(User user) {
        String displayName = user.getName();
        if (displayName == null || displayName.isEmpty()) {
            displayName = user.getEmail().split("@")[0];
        }

        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("name", displayName)
                .claim("email", user.getEmail()) // Email is added as a claim
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 10 * 60000))
                .signWith(getSigningKey())
                .compact();
    }

    // For OAuth2 / Fallback flows
    public String generateToken(String email) {
        String displayName = email.split("@")[0];

        return Jwts.builder()
                .subject(email)
                .claim("name", displayName)
                .claim("email", email) // Email is added as a claim
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 10 * 60000))
                .signWith(getSigningKey())
                .compact();
    }

    // FIXED: Added this method to extract the "email" claim for AuthController
    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("email", String.class);
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenExpired(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }
}