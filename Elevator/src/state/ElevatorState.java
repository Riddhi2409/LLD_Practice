package state;

import entity.Elevator;
import entity.Request;
import enums.Direction;

public abstract class ElevatorState {
    public Elevator elevator;

    public ElevatorState(Elevator elevator) {
        this.elevator = elevator;
    }

    abstract public Direction getDirection();
    abstract public void move();
    abstract public void addRequest(Request request);
}
