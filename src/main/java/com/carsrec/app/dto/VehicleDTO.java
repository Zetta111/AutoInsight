package com.carsrec.app.dto;

import com.carsrec.app.entity.Vehicle;
import java.math.BigDecimal;

public class VehicleDTO {

    private Long id;
    private String make;
    private String model;
    private Integer year;
    private Integer horsepower;
    private Integer torque;
    private Integer weight;
    private BigDecimal msrp;
    private Double powerToWeightRatio;  // horsepower per 1000 lbs

    public static VehicleDTO from(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.id = vehicle.getId();
        dto.make = vehicle.getMake();
        dto.model = vehicle.getModel();
        dto.year = vehicle.getYear();
        dto.horsepower = vehicle.getHorsepower();
        dto.torque = vehicle.getTorque();
        dto.weight = vehicle.getWeight();
        dto.msrp = vehicle.getMsrp();
        dto.powerToWeightRatio = calculatePowerToWeight(vehicle.getHorsepower(), vehicle.getWeight());
        return dto;
    }

    private static Double calculatePowerToWeight(Integer horsepower, Integer weight) {
        if (horsepower == null || weight == null || weight == 0) return null;
        return (double) horsepower / weight * 1000;
    }

    public Long getId() { return id; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public Integer getYear() { return year; }
    public Integer getHorsepower() { return horsepower; }
    public Integer getTorque() { return torque; }
    public Integer getWeight() { return weight; }
    public BigDecimal getMsrp() { return msrp; }
    public Double getPowerToWeightRatio() { return powerToWeightRatio; }
}
