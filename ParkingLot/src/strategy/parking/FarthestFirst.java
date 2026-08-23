package strategy.parking;

import Vehicle.Vehicle;
import entity.ParkingFloor;
import entity.ParkingSpot;

import java.util.*;

public class FarthestFirst implements ParkingStrategy{
    @Override
    public Optional<ParkingSpot> findSpots(List<ParkingFloor> floors, Vehicle vehicle) {
        List<ParkingFloor> revFloors=new ArrayList<>(floors);
        Collections.reverse(revFloors);

        for (ParkingFloor floor: revFloors){
            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);
            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();

    }
}
