package service;

import entity.Elevator;
import entity.Request;
import enums.Direction;
import enums.RequestSource;
import observer.ElevatorDisplay;
import strategy.ElevatorSelectionStrategy;
import strategy.NearestElevatorStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorService {
    private static volatile ElevatorService instance;
    private Map<Integer, Elevator> elevators;
    private ElevatorSelectionStrategy elevatorSelectionStrategy;
    private ExecutorService executorService;


    private ElevatorService(int numElevators){
        executorService= Executors.newFixedThreadPool(numElevators);
        elevators=new HashMap<>();
        ElevatorDisplay elevatorDisplay = new ElevatorDisplay();
        elevatorSelectionStrategy=new NearestElevatorStrategy();
        for(int i=1;i<=numElevators;i++){
            Elevator e1=new Elevator(i);
            e1.addObserver(elevatorDisplay);
            elevators.put(i,e1);
        }
    }

    public static ElevatorService getInstance(int numElevators){
        if(instance==null){
            synchronized (ElevatorService.class){
                if(instance==null){
                    instance=new ElevatorService(numElevators);
                }
            }
        }
        return instance;
    }

    public void start() {
        for (Elevator elevator : elevators.values()) {
            System.out.println("Starting elevator " + elevator.getId());
            executorService.submit(elevator);
        }
    }

    // --- Facade Methods ---

    // EXTERNAL Request (Hall Call)
    public void requestElevator(int floor, Direction direction) {
        System.out.println("\n>> EXTERNAL Request: User at floor " + floor + " wants to go " + direction);
        Request request = new Request(floor, direction, RequestSource.EXTERNAL);

        // Use strategy to find the best elevator
        Optional<Elevator> selectedElevator = elevatorSelectionStrategy.selectElevator(new ArrayList<>(elevators.values()), request);

        if(selectedElevator.isPresent()) {
            selectedElevator.get().addRequest(request);
        } else {
            System.out.println("System busy, please wait.");
        }
    }

    // INTERNAL Request (Cabin Call)
    public void selectFloor(int elevatorId, int destinationFloor) {
        System.out.println("\n>> INTERNAL Request: User in Elevator " + elevatorId + " selected floor " + destinationFloor);
        Request request = new Request(destinationFloor, Direction.IDLE, RequestSource.INTERNAL);

        Elevator elevator = elevators.get(elevatorId);
        if (elevator != null) {
            elevator.addRequest(request);
        } else {
            System.err.println("Invalid elevator ID.");
        }
    }

    public void shutdown() {
        System.out.println("Shutting down elevator system...");
        for (Elevator elevator : elevators.values()) {
            elevator.stopElevator();
        }
        executorService.shutdown();
    }




}
