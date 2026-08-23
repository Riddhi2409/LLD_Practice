package entity;

import enums.Direction;
import observer.ElevatorObserver;
import state.ElevatorState;
import state.IdleState;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator implements Runnable{
    private final int  id;
    private boolean isRunning;
    private AtomicInteger currentFloor;

    private ElevatorState state;
    private final TreeSet<Integer> upReqs;
    private final TreeSet<Integer> downReqs;

    private final List<ElevatorObserver> observers=new ArrayList<>();

    public Elevator(int id) {
        this.id = id;
        this.upReqs = new TreeSet<>();
        this.downReqs = new TreeSet<>((a,b)->b-a);
        this.currentFloor = new AtomicInteger(1);
        isRunning=true;
        this.state=new IdleState(this);
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
        observer.update(this); // Send initial state
    }

    public void notifyObservers() {
        for (ElevatorObserver observer : observers) {
            observer.update(this);
        }
    }

    public void move() {
        state.move();
    }

    // --- Request Handling ---
    public synchronized void addRequest(Request request) {
        System.out.println("Elevator " + id + " processing: " + request);
        state.addRequest(request);
    }

    public TreeSet<Integer> getUpReqs() {
        return upReqs;
    }

    public TreeSet<Integer> getDownReqs() {
        return downReqs;
    }

    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public int getId() {
        return id;
    }

    public void setCurrentFloor(int floor) {
        this.currentFloor.set(floor);
        notifyObservers();
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public Direction getDirection() {
        return state.getDirection();
    }

    public boolean isRunning() { return isRunning; }
    public void stopElevator() { this.isRunning = false; }

    public void run() {
        while (isRunning) {
            System.out.println(
                    "Elevator " + id + " running. State = " +
                            state.getClass().getSimpleName()
            );

            move();
            try {
                Thread.sleep(1000); // Simulate movement time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }

}
