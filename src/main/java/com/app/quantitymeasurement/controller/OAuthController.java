package com.app.quantitymeasurement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @GetMapping("/info")
    public Map<String, String> getOAuthInfo() {
        return Map.of("status", "OAuth flow is managed by SecurityConfig success handler");
    }
}