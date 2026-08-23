package strategy.parking;

import Vehicle.Vehicle;
import entity.ParkingFloor;
import entity.ParkingSpot;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findSpots(List<ParkingFloor> floors, Vehicle vehicle);
}
