package entity;

import enums.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private User user;
    private String vehicleId;
    private LocalDate startTime;
    private LocalDate endTime;
    private ReservationStatus status;
    private double totalAmount;

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Reservation(int id, User user, String vehicle, LocalDate startTime, LocalDate endTime) {
        this.id = id;
        this.user = user;
        this.vehicleId = vehicle;
        this.startTime = startTime;
        this.endTime = endTime;
        status=ReservationStatus.SCHEDULED;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
