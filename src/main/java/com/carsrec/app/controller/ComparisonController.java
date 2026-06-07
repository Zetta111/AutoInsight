package com.carsrec.app.controller;

import com.carsrec.app.dto.ComparisonDTO;
import com.carsrec.app.service.ComparisonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Handles incoming HTTP requests for vehicle comparisons
@RestController
@RequestMapping("/api/comparison")
public class ComparisonController {

    private final ComparisonService comparisonService;

    // Injects ComparisonService to delegate all comparison logic
    public ComparisonController(ComparisonService comparisonService) {
        this.comparisonService = comparisonService;
    }

    // GET /api/comparison?vehicleAId=1&vehicleBId=5
    // Public endpoint — no login required. Both vehicles must exist in our database.
    // Returns 404 if either vehicle ID is not found.
    @GetMapping
    public ResponseEntity<ComparisonDTO> compare(@RequestParam Long vehicleAId,
                                                  @RequestParam Long vehicleBId) {
        return ResponseEntity.ok(comparisonService.compare(vehicleAId, vehicleBId));
    }
}
