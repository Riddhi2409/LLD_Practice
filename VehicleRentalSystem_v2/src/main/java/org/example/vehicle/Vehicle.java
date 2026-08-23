package org.example.vehicle;

import org.example.vehicle.enums.VehicleStatus;
import org.example.vehicle.enums.VehicleType;

public class Vehicle {
    public int vehicleID;
    public VehicleType vehicleType;
    public volatile VehicleStatus vehicleStatus;
    public String vehicleNumber;
    public double dailyRentalCost;

    public Vehicle(int vehicleID, String vehicleNumber, VehicleType vehicleType) {
        this.vehicleID = vehicleID;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.vehicleStatus = VehicleStatus.AVAILABLE;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getDailyRentalCost() {
        return dailyRentalCost;
    }

    public void setDailyRentalCost(double dailyRentalCost) {
        this.dailyRentalCost = dailyRentalCost;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }
}
