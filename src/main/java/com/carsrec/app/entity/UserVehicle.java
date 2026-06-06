package com.carsrec.app.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Represents a row in the user_vehicles table — the link between a user and a vehicle
@Entity
@Table(name = "user_vehicles")
public class UserVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user who owns this entry — many user_vehicles can belong to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // The vehicle this entry points to — many user_vehicles can reference one vehicle
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    // Optional custom name the user gives their vehicle (e.g. "My Daily Driver")
    private String nickname;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public UserVehicle() {}

    // Automatically sets created_at to the current time when the row is first saved
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Vehicle getVehicle() { return vehicle; }
    public String getNickname() { return nickname; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
    public void setNickname(String nickname) { this.nickname = nickname; }
}
