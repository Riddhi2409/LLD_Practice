package services;

import entity.Reservation;
import entity.ReservationRepo;
import entity.Vehicle;
import enums.VehicleStatus;
import enums.VehicleType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class InventoryManager {
    // vehicleId -> vehicle
    private final ConcurrentMap<String,Vehicle> vehicles=new ConcurrentHashMap<>();

    // vehicleId → list of reservation IDs (metadata index)
    private final ConcurrentMap<String, List<Integer>> vehicleBookingIds=new ConcurrentHashMap<>();

    // per-vehicle lock
    private final ConcurrentMap<String, ReentrantLock> vehicleLocks = new ConcurrentHashMap<>();

    private ReservationRepo reservationRepository;

    public void addVehicle(Vehicle vehicle){
        vehicles.putIfAbsent(vehicle.getResgistrationNum(),vehicle);
    }

    public Optional<Vehicle> getVehicle(String vehicleId){
        return Optional.ofNullable(vehicles.get(vehicleId));
    }

    public ReentrantLock lockForVehicle(String vehicleId) {
        vehicleLocks.putIfAbsent(vehicleId, new ReentrantLock());
        return vehicleLocks.get(vehicleId);
    }

    public boolean isAvailable(String vehicleId, LocalDate from, LocalDate to){

        Vehicle vehicle=vehicles.get(vehicleId);
        if(vehicle==null){
            return false;
        }

        if(vehicle.getVehicleStatus()== VehicleStatus.UNDER_MAINTENANCE) return false;

        List<Integer> reservations=vehicleBookingIds.get(vehicleId);


        if(reservations == null || reservations.isEmpty()) {
            return true;
        }

        for(Integer id: reservations){
            Reservation reservation=reservationRepository.findById(id).get();
        }


        for(Integer id:reservations){
            Reservation reservation=reservationRepository.findById(id).get();
            if(reservation.getStartTime().isBefore(to) && reservation.getEndTime().isAfter(from)){
                return false;
            }

        }
        return true;

    }

    public Boolean reserve(String vehicleId,int reservationId, LocalDate from, LocalDate to){
        ReentrantLock lock=lockForVehicle(vehicleId);
        lock.lock();
        try {
            if(isAvailable(vehicleId,from,to)==false){
                return false;
            }
            vehicleBookingIds.putIfAbsent(vehicleId,new ArrayList<>());
            vehicleBookingIds.get(vehicleId).add(reservationId);
            vehicles.get(vehicleId).setVehicleStatus(VehicleStatus.RENTED);
            return true;
        }finally {
            lock.unlock();
        }
    }

    public void release(String vehicleId, int reservationId) {

        ReentrantLock lock = lockForVehicle(vehicleId);
        lock.lock();

        try {
            // remove reservation Id
            List<Integer> ids = vehicleBookingIds.get(vehicleId);
            if (ids != null) {
                ids.remove(Integer.valueOf(reservationId));
            }
            // if no more bookings → available
            List<Integer> stillBooked = vehicleBookingIds.get(vehicleId);
            if (stillBooked == null || stillBooked.isEmpty()) {
                vehicles.get(vehicleId).setVehicleStatus(VehicleStatus.AVAILABLE);
            }

        } finally {
            lock.unlock();
        }
    }

    public List<Vehicle> getAvailableVehicles(VehicleType type,
                                              LocalDate from,
                                              LocalDate to){
        return vehicles.values()
                .stream()
                .filter(vehicle -> vehicle.getVehicleType()==type)
                .filter(vehicle -> isAvailable(vehicle.getResgistrationNum(),from,to))
                .collect(Collectors.toList());
    }

    public void setReservationRepository(ReservationRepo reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

}
