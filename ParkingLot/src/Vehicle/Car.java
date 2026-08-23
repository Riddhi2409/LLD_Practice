package Vehicle;

import enums.VehicleSize;

public class Car extends Vehicle{
    public Car(String license){
        super(VehicleSize.MEDIUM,license);
    }
}
