package strategy;

import entity.Elevator;
import entity.Request;

import java.util.List;
import java.util.Optional;

public interface ElevatorSelectionStrategy {

    public Optional<Elevator> selectElevator(List<Elevator> elevators, Request request);
    boolean isSuitable(Elevator elevator,Request request);

}
