package com.carsrec.app.dto;

import java.math.BigDecimal;

// Holds the full comparison report between two vehicles,
// including each vehicle's stats and the calculated differences
public class ComparisonDTO {

    private VehicleDTO vehicleA;
    private VehicleDTO vehicleB;

    // Positive difference in horsepower between the two vehicles
    private Integer horsepowerDifference;

    // Which vehicle has more horsepower
    private String horsepowerWinner;

    // Positive difference in torque between the two vehicles
    private Integer torqueDifference;

    // Which vehicle has more torque
    private String torqueWinner;

    // Positive difference in power-to-weight ratio between the two vehicles
    private Double powerToWeightDifference;

    // Which vehicle has the better power-to-weight ratio
    private String powerToWeightWinner;

    // Positive difference in MSRP between the two vehicles
    private BigDecimal msrpDifference;

    // Which vehicle is cheaper (lower MSRP = better value for the buyer)
    private String msrpWinner;

    public VehicleDTO getVehicleA() { return vehicleA; }
    public VehicleDTO getVehicleB() { return vehicleB; }
    public Integer getHorsepowerDifference() { return horsepowerDifference; }
    public String getHorsepowerWinner() { return horsepowerWinner; }
    public Integer getTorqueDifference() { return torqueDifference; }
    public String getTorqueWinner() { return torqueWinner; }
    public Double getPowerToWeightDifference() { return powerToWeightDifference; }
    public String getPowerToWeightWinner() { return powerToWeightWinner; }
    public BigDecimal getMsrpDifference() { return msrpDifference; }
    public String getMsrpWinner() { return msrpWinner; }

    public void setVehicleA(VehicleDTO vehicleA) { this.vehicleA = vehicleA; }
    public void setVehicleB(VehicleDTO vehicleB) { this.vehicleB = vehicleB; }
    public void setHorsepowerDifference(Integer horsepowerDifference) { this.horsepowerDifference = horsepowerDifference; }
    public void setHorsepowerWinner(String horsepowerWinner) { this.horsepowerWinner = horsepowerWinner; }
    public void setTorqueDifference(Integer torqueDifference) { this.torqueDifference = torqueDifference; }
    public void setTorqueWinner(String torqueWinner) { this.torqueWinner = torqueWinner; }
    public void setPowerToWeightDifference(Double powerToWeightDifference) { this.powerToWeightDifference = powerToWeightDifference; }
    public void setPowerToWeightWinner(String powerToWeightWinner) { this.powerToWeightWinner = powerToWeightWinner; }
    public void setMsrpDifference(BigDecimal msrpDifference) { this.msrpDifference = msrpDifference; }
    public void setMsrpWinner(String msrpWinner) { this.msrpWinner = msrpWinner; }

    // Provides a fluent builder for constructing a ComparisonDTO
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ComparisonDTO dto = new ComparisonDTO();

        public Builder vehicleA(VehicleDTO v) { dto.vehicleA = v; return this; }
        public Builder vehicleB(VehicleDTO v) { dto.vehicleB = v; return this; }
        public Builder horsepowerDifference(Integer d) { dto.horsepowerDifference = d; return this; }
        public Builder horsepowerWinner(String w) { dto.horsepowerWinner = w; return this; }
        public Builder torqueDifference(Integer d) { dto.torqueDifference = d; return this; }
        public Builder torqueWinner(String w) { dto.torqueWinner = w; return this; }
        public Builder powerToWeightDifference(Double d) { dto.powerToWeightDifference = d; return this; }
        public Builder powerToWeightWinner(String w) { dto.powerToWeightWinner = w; return this; }
        public Builder msrpDifference(BigDecimal d) { dto.msrpDifference = d; return this; }
        public Builder msrpWinner(String w) { dto.msrpWinner = w; return this; }

        // Returns the fully built ComparisonDTO
        public ComparisonDTO build() { return dto; }
    }
}
