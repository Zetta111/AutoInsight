package com.carsrec.app.service;

import com.carsrec.app.dto.AddUserVehicleRequest;
import com.carsrec.app.dto.MessageResponse;
import com.carsrec.app.dto.UserVehicleDTO;
import com.carsrec.app.entity.User;
import com.carsrec.app.entity.UserVehicle;
import com.carsrec.app.entity.Vehicle;
import com.carsrec.app.exception.ResourceNotFoundException;
import com.carsrec.app.repository.UserRepository;
import com.carsrec.app.repository.UserVehicleRepository;
import com.carsrec.app.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Handles all logic for adding and retrieving vehicles in a user's garage
@Service
public class UserVehicleService {

    private final UserVehicleRepository userVehicleRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    // Injects repositories needed to look up users, vehicles, and user_vehicle records
    public UserVehicleService(UserVehicleRepository userVehicleRepository,
                               UserRepository userRepository,
                               VehicleRepository vehicleRepository) {
        this.userVehicleRepository = userVehicleRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    // Looks up the user and vehicle by their IDs, creates a new user_vehicle row linking them,
    // and returns a success message
    public MessageResponse addVehicle(String email, AddUserVehicleRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + request.getVehicleId()));

        UserVehicle userVehicle = new UserVehicle();
        userVehicle.setUser(user);
        userVehicle.setVehicle(vehicle);
        userVehicle.setNickname(request.getNickname());

        userVehicleRepository.save(userVehicle);

        return new MessageResponse("Vehicle successfully added to garage");
    }

    // Fetches all user_vehicle rows for the logged-in user and maps each one to a UserVehicleDTO
    public List<UserVehicleDTO> getUserVehicles(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return userVehicleRepository.findByUserId(user.getId())
                .stream()
                .map(UserVehicleDTO::from)
                .toList();
    }
}
