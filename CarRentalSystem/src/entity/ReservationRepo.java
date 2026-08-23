package entity;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ReservationRepo {
    private final ConcurrentMap<Integer, Reservation> reservations;

    public ReservationRepo() {
        this.reservations = new ConcurrentHashMap<>();
    }

    public void save(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }

    // Lookup reservation by ID
    public Optional<Reservation> findById(int reservationId) {
        return Optional.ofNullable(reservations.get(reservationId));
    }

    // Delete reservation from repository
    public void remove(int reservationId) {
        reservations.remove(reservationId);
    }

    // Return all reservations (for reporting/debug)
    public ConcurrentMap<Integer, Reservation> getAll() {
        return reservations;
    }

    public void setAmount(int id,double amount){
        reservations.get(id).setTotalAmount(amount);
    }

}
