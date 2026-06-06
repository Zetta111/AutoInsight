package com.carsrec.app.dto;

import com.carsrec.app.entity.UserVehicle;

import java.time.LocalDateTime;

public class UserVehicleDTO {

    private Long id;
    private String make;
    private String model;
    private Integer year;
    private String nickname;
    private LocalDateTime addedAt;

    // Builds a UserVehicleDTO from a UserVehicle entity, pulling vehicle details from the linked vehicle
    public static UserVehicleDTO from(UserVehicle userVehicle) {
        UserVehicleDTO dto = new UserVehicleDTO();
        dto.id = userVehicle.getId();
        dto.make = userVehicle.getVehicle().getMake();
        dto.model = userVehicle.getVehicle().getModel();
        dto.year = userVehicle.getVehicle().getYear();
        dto.nickname = userVehicle.getNickname();
        dto.addedAt = userVehicle.getCreatedAt();
        return dto;
    }

    public Long getId() { return id; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public Integer getYear() { return year; }
    public String getNickname() { return nickname; }
    public LocalDateTime getAddedAt() { return addedAt; }
}
