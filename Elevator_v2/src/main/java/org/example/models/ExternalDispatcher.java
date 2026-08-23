package org.example.models;

import org.example.enums.ElevatorDirection;

public class ExternalDispatcher {
    ElevatorScheduler scheduler;
    public ExternalDispatcher(ElevatorScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public ElevatorController submitExternalRequest(int floor, ElevatorDirection direction) {

        ElevatorController controller =
                scheduler.assignElevator(floor, direction);
        controller.addPickupRequest(floor, direction);

        return controller;
    }
}
