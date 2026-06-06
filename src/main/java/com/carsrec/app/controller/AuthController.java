package com.carsrec.app.controller;

import com.carsrec.app.dto.AuthResponse;
import com.carsrec.app.dto.LoginRequest;
import com.carsrec.app.dto.MessageResponse;
import com.carsrec.app.dto.RegisterRequest;
import com.carsrec.app.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Handles all incoming HTTP requests related to authentication
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // Injects AuthService to delegate all auth logic
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // POST /api/auth/register — accepts email and password, creates a new account
    // Returns 201 with a success message if registration is successful
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    // POST /api/auth/login — accepts email and password, returns a JWT token on success
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
