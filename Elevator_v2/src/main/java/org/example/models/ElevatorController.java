package org.example.models;

import org.example.enums.ElevatorDirection;

import java.util.concurrent.PriorityBlockingQueue;

public class ElevatorController implements Runnable {

    public ElevatorCar elevatorCar;
    PriorityBlockingQueue<Integer> upMinPQ;
    PriorityBlockingQueue<Integer> downMaxPQ;

    private final Object monitor = new Object();

    public ElevatorController(ElevatorCar elevatorCar) {

        this.elevatorCar = elevatorCar;
        upMinPQ = new PriorityBlockingQueue<>();
        downMaxPQ = new PriorityBlockingQueue<>(10, (a, b) -> b - a);
    }

    public void submitRequest(int destinationFloor) {
        enqueueRequest(destinationFloor);
    }

    public void addPickupRequest(int floor, ElevatorDirection direction) {

        System.out.println("Pickup request at floor: " + floor +
                " accepted by elevator:" + elevatorCar.id);

        int currentFloor = elevatorCar.currentFloor;

        // decide queue based on direction + position
        if (direction == ElevatorDirection.UP) {

            if (floor >= currentFloor) {
                if (!upMinPQ.contains(floor))
                    upMinPQ.offer(floor);
            } else {
                if (!downMaxPQ.contains(floor))
                    downMaxPQ.offer(floor);
            }

        } else { // DOWN request

            if (floor <= currentFloor) {
                if (!downMaxPQ.contains(floor))
                    downMaxPQ.offer(floor);
            } else {
                if (!upMinPQ.contains(floor))
                    upMinPQ.offer(floor);
            }
        }

        synchronized (monitor) {
            monitor.notify();
        }
    }

    private void enqueueRequest(int destinationFloor){
        System.out.println("Request details-> destinationFloor: " + destinationFloor + " accepted by elevator:" + elevatorCar.id);

        if (destinationFloor == elevatorCar.nextFloorStoppage){
            return;
        }

        if(destinationFloor>=elevatorCar.nextFloorStoppage){
            if(!upMinPQ.contains(destinationFloor)){
                upMinPQ.offer(destinationFloor);
            }
        }
        else{
            if(!downMaxPQ.contains(destinationFloor)){
                downMaxPQ.offer(destinationFloor);
            }
        }
        synchronized(monitor){
            monitor.notify();
        }

    }

    @Override
    public void run() {
        controlElevator();
    }

    private void controlElevator() {
        while (true){
            synchronized (monitor){
                while (upMinPQ.isEmpty() && downMaxPQ.isEmpty()){
                    try{
                        System.out.println("elevator:" + elevatorCar.id + " is IDLE");
                        elevatorCar.movingDirection = ElevatorDirection.IDLE;
                        monitor.wait(); // sleep until request arrives
                    }catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            while (!upMinPQ.isEmpty()) {
                int floor = upMinPQ.poll();
                System.out.println("Serving floor: " + floor + " by elevator:" + elevatorCar.id + " currentFloor: " + elevatorCar.currentFloor);
                elevatorCar.moveElevator(floor);
            }


            while (!downMaxPQ.isEmpty()) {
                int floor = downMaxPQ.poll();
                System.out.println("Serving floor: " + floor + " by elevator:" + elevatorCar.id + " currentFloor: " + elevatorCar.currentFloor);
                elevatorCar.moveElevator(floor);
            }
        }
    }

}
