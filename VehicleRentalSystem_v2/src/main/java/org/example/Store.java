package org.example;

import org.example.bill.Bill;
import org.example.bill.BillingManager;
import org.example.bill.BillingStrategy;
import org.example.bill.DailyBillingStrategy;
import org.example.payment.Payment;
import org.example.payment.PaymentManager;
import org.example.payment.PaymentStrategy;
import org.example.payment.UPIPaymentStrategy;
import org.example.reservation.Reservation;
import org.example.reservation.ReservationManager;
import org.example.reservation.enums.ReservationType;
import org.example.vehicle.Vehicle;
import org.example.vehicle.VehicleInventoryManager;
import org.example.vehicle.enums.VehicleType;
import org.example.Location;

import java.time.LocalDate;
import java.util.List;

public class Store {
    private final int storeId;
    private final Location storeLocation;
    private final VehicleInventoryManager inventory;
    private final ReservationManager reservationManager;

    private final BillingManager billManager;
    private final PaymentManager paymentManager;

    public Store(int storeId, Location location) {

        this.storeId = storeId;
        this.storeLocation = location;
        this.inventory = new VehicleInventoryManager();
        this.billManager = new BillingManager(new DailyBillingStrategy(inventory)); //default
        this.paymentManager = new PaymentManager(new UPIPaymentStrategy()); //default
        this.reservationManager = new ReservationManager(inventory);
    }

    // ----------------- Search Vehicles --------------------

    public List<Vehicle> getVehicles(VehicleType type, LocalDate from, LocalDate to) {
        return inventory.getAvailableVehicles(type, from, to);
    }

    // ----------------- Create Reservation -----------------
    public Reservation createReservation(int vehicleId, User user, LocalDate from, LocalDate to,
                                         ReservationType type) throws Exception {
        return reservationManager.createReservation(vehicleId, user, from, to, type);
    }

    public Bill generateBill(int reservationId, BillingStrategy billingStrategy) {
        Reservation r = reservationManager.findByID(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        billManager.setBillingStrategy(billingStrategy);
        return billManager.generateBill(r);
    }

    public Payment makePayment(Bill bill, PaymentStrategy paymentStrategy, double paymentAmount) {

        paymentManager.setPaymentStrategy(paymentStrategy);
        Payment payment = paymentManager.makePayment(bill, paymentAmount);

        if (!bill.isBillPaid()) {
            throw new RuntimeException("Payment failed");
        }

        // NOW we can safely remove the reservation from the repo
        reservationManager.remove(bill.getReservationId());
        return payment;
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



    public VehicleInventoryManager getInventory() {
        return inventory;
    }

    public int getStoreId() {
        return storeId;
    }
}
