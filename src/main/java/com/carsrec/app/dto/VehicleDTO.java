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
    private Integer cityMpg;
    private Integer highwayMpg;
    private Integer combinedMpg;
    private Double powerToWeightRatio;  // horsepower per 1000 lbs
    private Double costPerHp;           // dollars per unit of horsepower
    private Double costPerTorque;       // dollars per lb-ft of torque
    private Double costPerCombinedMpg;  // dollars per combined MPG point

    // Builds a VehicleDTO from a Vehicle entity, including all calculated performance metrics
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
        dto.cityMpg = vehicle.getCityMpg();
        dto.highwayMpg = vehicle.getHighwayMpg();
        dto.combinedMpg = vehicle.getCombinedMpg();
        dto.powerToWeightRatio = calculatePowerToWeight(vehicle.getHorsepower(), vehicle.getWeight());
        dto.costPerHp = calculateCostPerHp(vehicle.getMsrp(), vehicle.getHorsepower());
        dto.costPerTorque = calculateCostPerTorque(vehicle.getMsrp(), vehicle.getTorque());
        dto.costPerCombinedMpg = calculateCostPerCombinedMpg(vehicle.getMsrp(), vehicle.getCombinedMpg());
        return dto;
    }

    // Returns how many dollars the buyer pays per unit of horsepower
    private static Double calculateCostPerHp(BigDecimal msrp, Integer horsepower) {
        if (msrp == null || horsepower == null || horsepower == 0) return null;
        return msrp.doubleValue() / horsepower;
    }

    // Returns how many dollars the buyer pays per lb-ft of torque
    private static Double calculateCostPerTorque(BigDecimal msrp, Integer torque) {
        if (msrp == null || torque == null || torque == 0) return null;
        return msrp.doubleValue() / torque;
    }

    // Returns how many dollars the buyer pays per combined MPG point
    private static Double calculateCostPerCombinedMpg(BigDecimal msrp, Integer combinedMpg) {
        if (msrp == null || combinedMpg == null || combinedMpg == 0) return null;
        return msrp.doubleValue() / combinedMpg;
    }

    // Calculates horsepower per 1000 lbs
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
    public Integer getCityMpg() { return cityMpg; }
    public Integer getHighwayMpg() { return highwayMpg; }
    public Integer getCombinedMpg() { return combinedMpg; }
    public Double getPowerToWeightRatio() { return powerToWeightRatio; }
    public Double getCostPerHp() { return costPerHp; }
    public Double getCostPerTorque() { return costPerTorque; }
    public Double getCostPerCombinedMpg() { return costPerCombinedMpg; }
}
