package com.andersonfariasdev.easycatalogbackend.controller;

import com.andersonfariasdev.easycatalogbackend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String token = authService.authenticate(credentials.get("username"), credentials.get("password"));
        if (token == null) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        return ResponseEntity.ok(Map.of("message", "Token is valid", "user", userDetails.getUsername()));
    }

}
