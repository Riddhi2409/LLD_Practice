package entity;

import Vehicle.Vehicle;
import enums.VehicleSize;

public class ParkingSpot {
    private String spotId;
    private VehicleSize spotSize;
    private  boolean isAvailable;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
        this.isAvailable = true;
        this.parkedVehicle = null;
    }

    public boolean isAvailable(){
        return this.isAvailable;
    }

    public void parkVehicle(Vehicle vehicle){
        this.parkedVehicle=vehicle;
        this.isAvailable=false;
    }

    public void unparkVehicle(){
        this.parkedVehicle=null;
        this.isAvailable=true;
    }

    public boolean canFitVehicle(Vehicle vehicle){
        if(!isAvailable) return false;

        switch (vehicle.getVehicleSize()){
            case SMALL:
                return spotSize == VehicleSize.SMALL || spotSize == VehicleSize.LARGE ||spotSize == VehicleSize.MEDIUM;
            case MEDIUM:
                return spotSize == VehicleSize.MEDIUM || spotSize == VehicleSize.LARGE;
            case LARGE:
                return spotSize == VehicleSize.LARGE;
            default:
                return false;
        }
    }

    public String getSpotId() {
        return spotId;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
}
