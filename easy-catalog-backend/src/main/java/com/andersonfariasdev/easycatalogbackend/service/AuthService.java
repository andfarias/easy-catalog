package com.andersonfariasdev.easycatalogbackend.service;

import com.andersonfariasdev.easycatalogbackend.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;

    private final Map<String, String> users = new HashMap<>();

    public AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        users.put("admin", "admin");
        users.put("user", "password");
    }

    public String authenticate(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            String role = username.equals("admin") ? "ADMIN" : "USER";
            return jwtUtil.generateToken(username, role);
        }
        return null;
    }
}
