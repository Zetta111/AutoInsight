package com.carsrec.app.service;

import com.carsrec.app.dto.VehicleDTO;
import com.carsrec.app.entity.Vehicle;
import com.carsrec.app.exception.ResourceNotFoundException;
import com.carsrec.app.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Handles all vehicle data retrieval and performance comparison logic
@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    // Injects the vehicle repository for DB access
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Fetches every vehicle in the DB and maps each one to a VehicleDTO
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(VehicleDTO::from)
                .toList();
    }

    // Fetches a single vehicle by its ID, throws 404 if it doesn't exist
    public VehicleDTO getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
        return VehicleDTO.from(vehicle);
    }

    // Returns the positive horsepower gap between two vehicles
    public Integer horsepowerDifference(Vehicle a, Vehicle b) {
        if (a.getHorsepower() == null || b.getHorsepower() == null) return null;
        int higher = Math.max(a.getHorsepower(), b.getHorsepower());
        int lower = Math.min(a.getHorsepower(), b.getHorsepower());
        return higher - lower;
    }

    // Returns the positive torque gap between two vehicles
    public Integer torqueDifference(Vehicle a, Vehicle b) {
        if (a.getTorque() == null || b.getTorque() == null) return null;
        int higher = Math.max(a.getTorque(), b.getTorque());
        int lower = Math.min(a.getTorque(), b.getTorque());
        return higher - lower;
    }

    // Returns the positive power-to-weight ratio gap between two vehicles
    public Double powerToWeightDifference(Vehicle a, Vehicle b) {
        Double ratioA = powerToWeight(a);
        Double ratioB = powerToWeight(b);
        if (ratioA == null || ratioB == null) return null;
        double higher = Math.max(ratioA, ratioB);
        double lower = Math.min(ratioA, ratioB);
        return higher - lower;
    }

    // Calculates a single vehicle's power-to-weight ratio (hp per 1000 lbs)
    private Double powerToWeight(Vehicle v) {
        if (v.getHorsepower() == null || v.getWeight() == null || v.getWeight() == 0) return null;
        return (double) v.getHorsepower() / v.getWeight() * 1000;
    }

    // Returns how many dollars the buyer is paying per unit of horsepower
    public Double costPerHp(Vehicle v) {
        if (v.getMsrp() == null || v.getHorsepower() == null || v.getHorsepower() == 0) return null;
        return v.getMsrp().doubleValue() / v.getHorsepower();
    }

    // Returns how many dollars the buyer is paying per lb-ft of torque
    public Double costPerTorque(Vehicle v) {
        if (v.getMsrp() == null || v.getTorque() == null || v.getTorque() == 0) return null;
        return v.getMsrp().doubleValue() / v.getTorque();
    }

    // Returns how many dollars the buyer is paying per combined MPG point
    public Double costPerCombinedMpg(Vehicle v) {
        if (v.getMsrp() == null || v.getCombinedMpg() == null || v.getCombinedMpg() == 0) return null;
        return v.getMsrp().doubleValue() / v.getCombinedMpg();
    }
}
