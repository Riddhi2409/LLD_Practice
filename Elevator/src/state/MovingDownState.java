package state;

import entity.Elevator;
import entity.Request;
import enums.Direction;
import enums.RequestSource;

public class MovingDownState extends ElevatorState {

    public MovingDownState(Elevator elevator){
        super(elevator);
    }

    @Override
    public Direction getDirection() {
        return Direction.DOWN;
    }

    @Override
    public void move() {
        if(elevator.getDownReqs().isEmpty()){
            elevator.setState(new IdleState(elevator));
            return;
        }
        int targetFloor=elevator.getDownReqs().first();
        elevator.setCurrentFloor(elevator.getCurrentFloor()-1);

        if (elevator.getCurrentFloor() == targetFloor) {
            System.out.println("Elevator " + elevator.getId() + " stopped at floor " + targetFloor);
            elevator.getDownReqs().pollFirst();
        }

        if (elevator.getDownReqs().isEmpty()) {
            elevator.setState(new IdleState(elevator));
        }

    }

    @Override
    public void addRequest(Request request) {
        //internal requests
        if(request.getSource()== RequestSource.INTERNAL){
            if(request.getTargetFloor()> elevator.getCurrentFloor()){
                elevator.getUpReqs().add(request.getTargetFloor());
            }
            else{
                elevator.getDownReqs().add(request.getTargetFloor());
            }
            return;
        }

        //external requests
        if (request.getDirection() == Direction.DOWN && request.getTargetFloor() <= elevator.getCurrentFloor()) {
            elevator.getDownReqs().add(request.getTargetFloor());
        } else if (request.getDirection() == Direction.UP) {
            elevator.getUpReqs().add(request.getTargetFloor());
        }
    }
}
