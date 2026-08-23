package org.example.models;

import org.example.enums.ElevatorDirection;

import java.util.List;

public class NearestElevatorStartegy implements ElevatorSelectionStrategy{
    @Override
    public ElevatorController selectElevator(
            List<ElevatorController> controllers,
            int requestFloor,
            ElevatorDirection requestDirection) {

        ElevatorController bestSameDir = null;
        int minSameDirDistance = Integer.MAX_VALUE;

        ElevatorController bestIdle = null;
        int minIdleDistance = Integer.MAX_VALUE;

        ElevatorController bestOpposite = null;
        int minOppositeDistance = Integer.MAX_VALUE;

        for (ElevatorController controller : controllers) {

            ElevatorCar car = controller.elevatorCar;
            int currentFloor = car.currentFloor;
            ElevatorDirection dir = car.movingDirection;

            int distance = Math.abs(currentFloor - requestFloor);

            // CASE 1 → Same Direction & will pass request floor
            if (dir == requestDirection) {

                boolean willPass =
                        (dir == ElevatorDirection.UP && currentFloor <= requestFloor) ||
                                (dir == ElevatorDirection.DOWN && currentFloor >= requestFloor);

                if (willPass && distance < minSameDirDistance) {
                    minSameDirDistance = distance;
                    bestSameDir = controller;
                }
            }

            // CASE 2 → Idle elevator
            else if (dir == ElevatorDirection.IDLE) {
                if (distance < minIdleDistance) {
                    minIdleDistance = distance;
                    bestIdle = controller;
                }
            }

            // CASE 3 → Opposite direction elevator
            else {
                if (distance < minOppositeDistance) {
                    minOppositeDistance = distance;
                    bestOpposite = controller;
                }
            }
        }

        if (bestSameDir != null) return bestSameDir;
        if (bestIdle != null) return bestIdle;
        return bestOpposite;
    }
}
