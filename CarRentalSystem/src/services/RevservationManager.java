package services;

import entity.Reservation;
import entity.ReservationRepo;
import entity.User;
import enums.ReservationStatus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class RevservationManager {
    private final ReservationRepo repo;
    private final InventoryManager inventory;

    private final AtomicInteger reservationIdGenerator = new AtomicInteger(20000);

    public RevservationManager(InventoryManager inventory) {
        this.inventory = inventory;
        repo=new ReservationRepo();
    }

    public Reservation createReservation(String vehicleId, User user,
                                         LocalDate from,
                                         LocalDate to){
        int reservationId=reservationIdGenerator.getAndIncrement();
        boolean reserved = inventory.reserve(vehicleId, reservationId, from, to);

        if (!reserved) {
            throw new RuntimeException("Vehicle not available for selected dates");
        }

        Reservation reservation=new Reservation(reservationId,user,vehicleId,from,to);
        repo.save(reservation);
        return reservation;
    }

    public void cancelReservation(int reservationId) {

        Optional<Reservation> opt = repo.findById(reservationId);
        if (!opt.isPresent()) {
            throw new RuntimeException("Reservation not found");
        }

        Reservation r = opt.get();
        r.setStatus(ReservationStatus.CANCELLED);

        inventory.release(
                r.getVehicleId(),
                r.getId());

        repo.remove(reservationId);
    }

    // ----------------- Start Trip -------------------------

    public void startTrip(int reservationId) {
        Reservation r = repo.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        r.setStatus(ReservationStatus.IN_USE);
    }

    // ----------------- Submit Vehicle ---------------------

    public void submitVehicle(int reservationId) {

        Reservation r = repo.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        r.setStatus(ReservationStatus.COMPLETED);
        LocalDate s=r.getStartTime();
        LocalDate d=r.getEndTime();
        long days=ChronoUnit.DAYS.between(s,d);
        double amout =inventory.getVehicle(r.getVehicleId()).get().calculateRentalFees(days);
        r.setTotalAmount(amout);

        inventory.release(
                r.getVehicleId(),
                r.getId()
        );
    }

    // ----------------- remove reservation ---------------------
    public void remove(int reservationId) {
        repo.remove(reservationId);
    }
}
