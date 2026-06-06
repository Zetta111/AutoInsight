package com.carsrec.app.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Represents a row in the vehicles table
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    // Stored as model_year in the DB because "year" is a reserved SQL keyword
    @Column(name = "model_year", nullable = false)
    private Integer year;

    private Integer horsepower;

    private Integer torque;

    private Integer weight;

    private BigDecimal msrp;

    // A vehicle can be linked to many users through user_vehicles.
    // Deleting a vehicle also deletes all its user_vehicle rows.
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserVehicle> userVehicles = new ArrayList<>();

    public Vehicle() {}

    public Long getId() { return id; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public Integer getYear() { return year; }
    public Integer getHorsepower() { return horsepower; }
    public Integer getTorque() { return torque; }
    public Integer getWeight() { return weight; }
    public BigDecimal getMsrp() { return msrp; }
    public List<UserVehicle> getUserVehicles() { return userVehicles; }

    public void setId(Long id) { this.id = id; }
    public void setMake(String make) { this.make = make; }
    public void setModel(String model) { this.model = model; }
    public void setYear(Integer year) { this.year = year; }
    public void setHorsepower(Integer horsepower) { this.horsepower = horsepower; }
    public void setTorque(Integer torque) { this.torque = torque; }
    public void setWeight(Integer weight) { this.weight = weight; }
    public void setMsrp(BigDecimal msrp) { this.msrp = msrp; }
}
