package com.carsrec.app.service;

import com.carsrec.app.dto.ComparisonDTO;
import com.carsrec.app.dto.VehicleDTO;
import com.carsrec.app.entity.Vehicle;
import com.carsrec.app.exception.ResourceNotFoundException;
import com.carsrec.app.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

// Handles the logic for comparing two vehicles and building the comparison report
@Service
public class ComparisonService {

    private final VehicleRepository vehicleRepository;
    private final VehicleService vehicleService;

    // Injects VehicleRepository to fetch both vehicles and VehicleService for the difference calculations
    public ComparisonService(VehicleRepository vehicleRepository, VehicleService vehicleService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleService = vehicleService;
    }

    // Validates that both vehicle IDs exist in our database, then runs all three
    // difference calculations and returns the full comparison report
    public ComparisonDTO compare(Long vehicleAId, Long vehicleBId) {
        Vehicle a = vehicleRepository.findById(vehicleAId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + vehicleAId));

        Vehicle b = vehicleRepository.findById(vehicleBId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + vehicleBId));

        String labelA = a.getMake() + " " + a.getModel();
        String labelB = b.getMake() + " " + b.getModel();

        return ComparisonDTO.builder()
                .vehicleA(VehicleDTO.from(a))
                .vehicleB(VehicleDTO.from(b))
                .horsepowerDifference(vehicleService.horsepowerDifference(a, b))
                .horsepowerWinner(resolveWinner(a.getHorsepower(), b.getHorsepower(), labelA, labelB))
                .torqueDifference(vehicleService.torqueDifference(a, b))
                .torqueWinner(resolveWinner(a.getTorque(), b.getTorque(), labelA, labelB))
                .powerToWeightDifference(vehicleService.powerToWeightDifference(a, b))
                .powerToWeightWinner(resolvePowerToWeightWinner(a, b, labelA, labelB))
                .msrpDifference(msrpDifference(a, b))
                .msrpWinner(resolveMsrpWinner(a.getMsrp(), b.getMsrp(), labelA, labelB))
                .build();
    }

    // Returns the label of whichever vehicle has the higher integer stat, or "Tie" if equal
    private String resolveWinner(Integer statA, Integer statB, String labelA, String labelB) {
        if (statA == null || statB == null) return "N/A";
        if (statA > statB) return labelA;
        if (statB > statA) return labelB;
        return "Tie";
    }

    // Returns the positive MSRP difference between the two vehicles
    private BigDecimal msrpDifference(Vehicle a, Vehicle b) {
        if (a.getMsrp() == null || b.getMsrp() == null) return null;
        return a.getMsrp().subtract(b.getMsrp()).abs();
    }

    // Returns the label of whichever vehicle is cheaper — lower MSRP wins for the buyer
    private String resolveMsrpWinner(BigDecimal msrpA, BigDecimal msrpB, String labelA, String labelB) {
        if (msrpA == null || msrpB == null) return "N/A";
        int cmp = msrpA.compareTo(msrpB);
        if (cmp < 0) return labelA;
        if (cmp > 0) return labelB;
        return "Tie";
    }

    // Returns the label of whichever vehicle has the better power-to-weight ratio, or "Tie" if equal
    private String resolvePowerToWeightWinner(Vehicle a, Vehicle b, String labelA, String labelB) {
        if (a.getHorsepower() == null || a.getWeight() == null ||
            b.getHorsepower() == null || b.getWeight() == null) return "N/A";
        double ratioA = (double) a.getHorsepower() / a.getWeight();
        double ratioB = (double) b.getHorsepower() / b.getWeight();
        if (ratioA > ratioB) return labelA;
        if (ratioB > ratioA) return labelB;
        return "Tie";
    }
}
