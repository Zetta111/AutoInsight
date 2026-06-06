package com.carsrec.app.controller;

import com.carsrec.app.dto.AddUserVehicleRequest;
import com.carsrec.app.dto.MessageResponse;
import com.carsrec.app.dto.UserVehicleDTO;
import com.carsrec.app.service.UserVehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handles all incoming HTTP requests for a user's garage (user_vehicles)
@RestController
@RequestMapping("/api/user-vehicles")
public class UserVehicleController {

    private final UserVehicleService userVehicleService;

    // Injects UserVehicleService to delegate all garage logic
    public UserVehicleController(UserVehicleService userVehicleService) {
        this.userVehicleService = userVehicleService;
    }

    // POST /api/user-vehicles — adds a vehicle to the logged-in user's garage
    // Requires a valid JWT; reads the user's email from the token
    @PostMapping
    public ResponseEntity<MessageResponse> addVehicle(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody AddUserVehicleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userVehicleService.addVehicle(userDetails.getUsername(), request));
    }

    // GET /api/user-vehicles — returns all vehicles in the logged-in user's garage
    // Requires a valid JWT; reads the user's email from the token
    @GetMapping
    public ResponseEntity<List<UserVehicleDTO>> getUserVehicles(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userVehicleService.getUserVehicles(userDetails.getUsername()));
    }
}
