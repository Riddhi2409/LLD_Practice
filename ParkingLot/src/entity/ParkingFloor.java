package entity;

import Vehicle.Vehicle;
import enums.VehicleSize;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingFloor {
    Map<String, ParkingSpot> spots;
    int floorNum;

    public ParkingFloor(int floorNum) {
        this.floorNum = floorNum;
        spots=new HashMap<>();
    }

     public void addSpot(ParkingSpot spot){
        spots.put(spot.getSpotId(), spot);
    }

    public ParkingSpot getSpot(String spotId){
        return spots.getOrDefault(spotId,null);
    }

    public synchronized Optional<ParkingSpot> findAvailableSpot(Vehicle vehicle){
        return spots.values().stream()
                .filter(spot -> spot.isAvailable() && spot.canFitVehicle(vehicle))
                .sorted(Comparator.comparing(ParkingSpot::getSpotSize))
                .findFirst();
    }


    public void displayAvailability(){
        System.out.printf("--- Floor %d Availability ---\n", floorNum);
        Map<VehicleSize, Long> availableCounts=spots.values().stream()
                .filter(spot -> spot.isAvailable())
                .collect(Collectors.groupingBy(ParkingSpot::getSpotSize,Collectors.counting()));
        for(VehicleSize size:VehicleSize.values()){
            System.out.printf("  %s spots: %d\n", size, availableCounts.getOrDefault(size, 0L));
        }
    }

}
