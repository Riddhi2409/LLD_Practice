package Vehicle;

import enums.VehicleSize;

public class Truck extends Vehicle{
    public Truck(String license){
        super(VehicleSize.LARGE,license);
    }
}
