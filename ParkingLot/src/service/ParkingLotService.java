package service;

import Vehicle.Vehicle;
import entity.ParkingFloor;
import entity.ParkingSpot;
import entity.ParkingTicket;
import strategy.fee.FeeStrategy;
import strategy.fee.FlatRateBasedFee;
import strategy.parking.BestFitStrategy;
import strategy.parking.ParkingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLotService {
    private static ParkingLotService instance;
    private FeeStrategy feeStrategy;
    private ParkingStrategy parkingStrategy;
    private List<ParkingFloor> floors;
    private Map<String, ParkingTicket> activeTickets;

    private ParkingLotService() {
        floors = new ArrayList<>();
        activeTickets = new ConcurrentHashMap<>();
        parkingStrategy = new BestFitStrategy();
        feeStrategy = new FlatRateBasedFee();
    }

    public static synchronized ParkingLotService getInstance() {
        if (instance == null) {
            instance = new ParkingLotService();
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> availableSpot = parkingStrategy.findSpots(floors, vehicle);

        if (availableSpot.isPresent()) {
            ParkingSpot spot = availableSpot.get();
            spot.parkVehicle(vehicle);
            ParkingTicket ticket = new ParkingTicket(spot, vehicle);
            activeTickets.put(vehicle.getLicensenum(), ticket);
            System.out.printf("%s parked at %s. Ticket: %s\n", vehicle.getLicensenum(), spot.getSpotId(), ticket.getTicketId());
            return Optional.of(ticket);
        }

        System.out.println("No available spot for " + vehicle.getLicensenum());
        return Optional.empty();
    }

    public Optional<Double> unparkVehicle(String licenseNumber) {
        ParkingTicket ticket = activeTickets.remove(licenseNumber);
        if (ticket == null) {
            System.out.println("Ticket not found");
            return Optional.empty();
        }

        ticket.setEndTime(LocalDateTime.now());
        ticket.getSpot().unparkVehicle();

        Double parkingFee = feeStrategy.calculateFee(ticket);

        return Optional.of(parkingFee);
    }
}