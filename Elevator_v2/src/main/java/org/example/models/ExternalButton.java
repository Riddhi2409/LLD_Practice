package org.example.models;

import org.example.enums.ElevatorDirection;

public class ExternalButton {

    private final ExternalDispatcher dispatcher;

    public ExternalButton(ExternalDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    // ⭐ NOW RETURNS CONTROLLER
    public ElevatorController pressButton(int floor,
                                          ElevatorDirection direction) {

        System.out.println("External Button pressed at floor "
                + floor + " Direction " + direction);

        return dispatcher.submitExternalRequest(floor, direction);
    }
}