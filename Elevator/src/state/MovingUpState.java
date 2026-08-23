package state;

import entity.Elevator;
import entity.Request;
import enums.Direction;
import enums.RequestSource;

public class MovingUpState extends ElevatorState{

    public MovingUpState(Elevator elevator){
        super(elevator);
    }

    @Override
    public Direction getDirection() {
        return Direction.UP;
    }

    @Override
    public void move() {
        if(elevator.getUpReqs().isEmpty()){
            elevator.setState(new IdleState(elevator));
            return;
        }
        int targetFloor=elevator.getUpReqs().first();
        elevator.setCurrentFloor(elevator.getCurrentFloor()+1);

        if (elevator.getCurrentFloor() == targetFloor) {
            System.out.println("Elevator " + elevator.getId() + " stopped at floor " + targetFloor);
            elevator.getUpReqs().pollFirst();
        }

        if (elevator.getDownReqs().isEmpty()) {
            elevator.setState(new IdleState(elevator));
        }
    }

    @Override
    public void addRequest(Request request) {
        // Internal requests always get added to the appropriate queue
        if (request.getSource() == RequestSource.INTERNAL) {
            if (request.getTargetFloor() > elevator.getCurrentFloor()) {
                elevator.getUpReqs().add(request.getTargetFloor());
            } else {
                elevator.getDownReqs().add(request.getTargetFloor());
            }
            return;
        }

        // External requests
        if (request.getDirection() == Direction.UP && request.getTargetFloor() >= elevator.getCurrentFloor()) {
            elevator.getUpReqs().add(request.getTargetFloor());
        } else if (request.getDirection() == Direction.DOWN) {
            elevator.getDownReqs().add(request.getTargetFloor());
        }
    }
}
