package com.carsrec.app.dto;

import jakarta.validation.constraints.NotNull;

public class AddUserVehicleRequest {

    // The ID of the vehicle from the vehicles table to add to the user's garage
    @NotNull
    private Long vehicleId;

    // Optional custom name the user wants to give their vehicle
    private String nickname;

    public Long getVehicleId() { return vehicleId; }
    public String getNickname() { return nickname; }

    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }
    public void setNickname(String nickname) { this.nickname = nickname; }
}
