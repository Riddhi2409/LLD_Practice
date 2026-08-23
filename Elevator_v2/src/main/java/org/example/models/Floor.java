package org.example.models;

import org.example.enums.ElevatorDirection;

public class Floor {

    int floorNumber;
    ExternalButton upButton;
    ExternalButton downButton;

    public Floor(int floorNumber, ExternalDispatcher dispatcher) {
        this.floorNumber = floorNumber;
        this.upButton = new ExternalButton(dispatcher);
        this.downButton = new ExternalButton(dispatcher);
    }

    public ElevatorController pressUpButton() {
        return upButton.pressButton(floorNumber, ElevatorDirection.UP);
    }

    public ElevatorController pressDownButton() {
        return downButton.pressButton(floorNumber, ElevatorDirection.DOWN);
    }
}