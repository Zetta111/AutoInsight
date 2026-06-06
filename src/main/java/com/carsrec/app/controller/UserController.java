package com.carsrec.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// Handles incoming HTTP requests related to the currently authenticated user
@RestController
@RequestMapping("/api/users")
public class UserController {

    // GET /api/users/me — returns the email and roles of the currently logged-in user
    // Requires a valid JWT in the Authorization header
    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(Map.of(
                "username", userDetails.getUsername(),
                "roles", userDetails.getAuthorities().toString()
        ));
    }
}
