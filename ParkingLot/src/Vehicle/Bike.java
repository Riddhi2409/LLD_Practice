package Vehicle;

import enums.VehicleSize;

public class Bike extends Vehicle{
    public Bike(String license){
        super(VehicleSize.SMALL,license);
    }
}
