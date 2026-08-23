package entity;

import enums.VehicleStatus;
import enums.VehicleType;

import java.util.concurrent.locks.ReentrantLock;

public class Vehicle {
    private final String resgistrationNum;
    private final String modelId;
    private final VehicleType vehicleType;
    private VehicleStatus vehicleStatus;
    private double baseRentalPrice;
//    private ReentrantLock vehicleLock;

    public Vehicle(String resgistrationNum, String modelId, VehicleType vehicleType, double baseRentalPrice) {
        this.resgistrationNum = resgistrationNum;
        this.modelId = modelId;
        this.vehicleType = vehicleType;
        this.vehicleStatus = VehicleStatus.AVAILABLE;
        this.baseRentalPrice = baseRentalPrice;
    }

    public String getResgistrationNum() {
        return resgistrationNum;
    }

    public String getModelId() {
        return modelId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }


    public double getBaseRentalPrice() {
        return baseRentalPrice;
    }

    public double calculateRentalFees(double hours){
        return hours*baseRentalPrice;
    }
}
