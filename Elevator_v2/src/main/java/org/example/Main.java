package org.example;

import org.example.enums.ElevatorDirection;
import org.example.models.NearestElevatorStartegy;
//import org.example.models.NearestElevatorStartegy;

public class Main {

    public static void main(String[] args) {

        try {

            ElevatorSystem system =
                    new ElevatorSystem(
                            5,   // number of floors
                            2,   // number of elevators
                            new NearestElevatorStartegy()
                    );

            system.startSystem();

            // External Call : Floor 3 UP
            int lift1 = system.requestLift(3, ElevatorDirection.UP);
            Thread.sleep(500);

            // Internal Call (user entered that lift)
            system.pressInternalButton(3, 5);
            Thread.sleep(500);


            // External Call : Floor 1 DOWN
            int lift2 = system.requestLift(1, ElevatorDirection.DOWN);
            Thread.sleep(500);

            // Internal Call
            system.pressInternalButton(1, 4);
            Thread.sleep(500);


            // Another external
            system.requestLift(2, ElevatorDirection.UP);
            Thread.sleep(500);

            system.pressInternalButton(2, 0);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}