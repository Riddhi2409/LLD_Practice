package entity;

import enums.VehicleStatus;
import enums.VehicleType;
import services.InventoryManager;
import services.RevservationManager;
import strategy.PaymentProcessor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalStore {
    private String storeId;
    private String name;
    private Location location;
    private final InventoryManager inventory;
    private final RevservationManager reservationManager;


    public RentalStore(String storeId, String name, Location location) {
        this.storeId = storeId;
        this.name = name;
        this.location = location;
        inventory=new InventoryManager();
        reservationManager=new RevservationManager(inventory);
    }

    public List<Vehicle> getVehicles(VehicleType type, LocalDate from, LocalDate to) {
        return inventory.getAvailableVehicles(type, from, to);
    }

    public void addVehicle(Vehicle vehicle){
        inventory.addVehicle(vehicle);
    }

    public Vehicle getVehicle(String registrationId){
        return inventory.getVehicle(registrationId).get();
    }


    public String getStoreId() {
        return storeId;
    }

    public InventoryManager getInventory() {
        return inventory;
    }

    public RevservationManager getReservationManager() {
        return reservationManager;
    }


    // ----------------- Create Reservation -----------------
    public Reservation createReservation(String vehicleId, User user, LocalDate from, LocalDate to) throws Exception {
        return reservationManager.createReservation(vehicleId, user, from, to);
    }

    public void makePayment(PaymentProcessor paymentStrategy, double paymentAmount) {
        paymentStrategy.processPayment(paymentAmount);
    }

    // ----------------- Update Reservation -----------------

    public void cancelReservation(int reservationId) {
        reservationManager.cancelReservation(reservationId);
    }

    public void startTrip(int reservationId) {
        reservationManager.startTrip(reservationId);
    }

    public void submitVehicle(int reservationId) {
        reservationManager.submitVehicle(reservationId);
    }





}
