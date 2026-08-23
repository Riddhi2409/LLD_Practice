package state;

import entity.Elevator;
import entity.Request;
import enums.Direction;

public class IdleState extends ElevatorState{

    public IdleState(Elevator elevator){
        super(elevator);
    }

    @Override
    public Direction getDirection() {
        return Direction.IDLE;
    }

    @Override
    public void move() {
        if(!elevator.getUpReqs().isEmpty()){
            elevator.setState(new MovingUpState(elevator));
        }
        else if(!elevator.getDownReqs().isEmpty()){
            elevator.setState(new MovingDownState(elevator));
        }
    }

    @Override
    public void addRequest(Request request) {
        if(request.getTargetFloor()>elevator.getCurrentFloor()){
            elevator.getUpReqs().add(request.getTargetFloor());
        }
        else if(request.getTargetFloor() < elevator.getCurrentFloor()){
            elevator.getDownReqs().add(request.getTargetFloor());
        }
    }
}
