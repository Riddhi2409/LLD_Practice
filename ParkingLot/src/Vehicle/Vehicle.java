package Vehicle;

import enums.VehicleSize;

public class Vehicle {
    private VehicleSize vehicleSize;
    private String licensenum;

    public Vehicle(VehicleSize vehicleSize, String licensenum) {
        this.vehicleSize = vehicleSize;
        this.licensenum = licensenum;
    }

    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }

    public String getLicensenum() {
        return licensenum;
    }
}
