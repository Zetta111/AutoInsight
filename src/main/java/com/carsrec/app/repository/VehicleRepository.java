package com.carsrec.app.repository;

import com.carsrec.app.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Provides DB access for the vehicles table.
// findAll, findById, save, delete, etc. are inherited from JpaRepository.
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // Looks up a specific vehicle by its make, model, and year — used to avoid duplicate entries
    Optional<Vehicle> findByMakeAndModelAndYear(String make, String model, Integer year);
}
