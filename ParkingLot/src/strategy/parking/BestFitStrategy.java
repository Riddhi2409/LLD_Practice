package strategy.parking;

import Vehicle.Vehicle;
import entity.ParkingFloor;
import entity.ParkingSpot;

import java.util.List;
import java.util.Optional;

public class BestFitStrategy implements ParkingStrategy{
    @Override
    public Optional<ParkingSpot> findSpots(List<ParkingFloor> floors, Vehicle vehicle) {
        Optional<ParkingSpot> bestFit= Optional.empty();
        for(ParkingFloor floor: floors){
            Optional<ParkingSpot> spotOnThisFloor = floor.findAvailableSpot(vehicle);
            if(spotOnThisFloor.isPresent()){
                if(bestFit.isEmpty()){
                    bestFit=spotOnThisFloor;
                }
                else if(bestFit.get().getSpotSize().ordinal()>spotOnThisFloor.get().getSpotSize().ordinal()) {
                    bestFit=spotOnThisFloor;
                }
            }
        }
        return bestFit;
    }
}
