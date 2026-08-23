package org.example;

import org.example.enums.ElevatorDirection;
import org.example.models.*;

import java.util.*;

public class ElevatorSystem {

    private Building building;
    private Map<Integer, ElevatorController> activeAssignments;
    private List<ElevatorController> controllers;

    public ElevatorSystem(int floors,
                          int elevators,
                          ElevatorSelectionStrategy strategy) {

        controllers = new ArrayList<>();
        activeAssignments = new HashMap<>();

        for (int i = 1; i <= elevators; i++) {

            ElevatorCar car = new ElevatorCar(i);
            ElevatorController controller =
                    new ElevatorController(car);

            controllers.add(controller);
        }

        ElevatorScheduler scheduler =
                new ElevatorScheduler(controllers, strategy);

        ExternalDispatcher dispatcher =
                new ExternalDispatcher(scheduler);

        building = new Building(floors, dispatcher);
    }

    public void startSystem() {

        for (ElevatorController c : controllers) {
            new Thread(c,
                    "Elevator-" + c.elevatorCar.id).start();
        }
    }

    // ⭐ client only calls this
    public int requestLift(int floor,
                           ElevatorDirection direction) {

        ElevatorController controller;

        if (direction == ElevatorDirection.UP)
            controller = building.getFloor(floor).pressUpButton();
        else
            controller = building.getFloor(floor).pressDownButton();

        activeAssignments.put(floor, controller);

        return controller.elevatorCar.id;
    }

    // ⭐ client uses elevatorId
    public void pressInternalButton(int pickupFloor,
                                    int destinationFloor) {

        ElevatorController controller =
                activeAssignments.get(pickupFloor);

        if (controller != null)
            controller.submitRequest(destinationFloor);
    }
}