package com.carsrec.app.repository;

import com.carsrec.app.entity.UserVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Provides DB access for the user_vehicles table.
// findAll, findById, save, delete, etc. are inherited from JpaRepository.
@Repository
public interface UserVehicleRepository extends JpaRepository<UserVehicle, Long> {

    // Returns all vehicles linked to a specific user — used to fetch a user's garage
    List<UserVehicle> findByUserId(Long userId);
}
