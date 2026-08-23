package strategy.parking;

import Vehicle.Vehicle;
import entity.ParkingFloor;
import entity.ParkingSpot;

import java.util.List;
import java.util.Optional;

public class NearestFirstStrategy implements ParkingStrategy{
    @Override
    public Optional<ParkingSpot> findSpots(List<ParkingFloor> floors, Vehicle vehicle) {
        for (ParkingFloor floor: floors){
            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);
            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();
    }
}
