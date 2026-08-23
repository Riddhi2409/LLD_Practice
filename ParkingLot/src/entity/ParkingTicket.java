package entity;

import Vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingTicket {
    private String ticketId;
    private ParkingSpot spot;
    private Vehicle vehicle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ParkingTicket(ParkingSpot spot, Vehicle vehicle) {
        this.ticketId= UUID.randomUUID().toString();
        this.spot = spot;
        this.vehicle = vehicle;
        this.startTime= LocalDateTime.now();
    }

    public String getTicketId() {
        return ticketId;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
