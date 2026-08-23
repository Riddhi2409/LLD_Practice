import entity.*;
import enums.VehicleType;
import services.CarRentalSystem;
import strategy.PayPalPaymentProcessor;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("\n===== LLD: Car Rental System Demo =====");

        CarRentalSystem rentalSystem = new CarRentalSystem();

        // ---------------------------------------------------------
        // 1. Create Stores in System
        // ---------------------------------------------------------

        // Create a store1
        Location store1Location = new Location(
                "45",
                "Area1",
                "City1",
                "State1"
        );
        RentalStore store1 = new RentalStore("1001", "ABC",store1Location);
        rentalSystem.addStore(store1);

        // ---------------------------------------------------------
        // 2. Create Users in System
        // ---------------------------------------------------------

        // Create 2 users
        User user1 = new User(801, "SJ", "DL2022GDG556690");
        User user2 = new User(802, "DJ", "DL2017DHW9090765231");
        rentalSystem.addUser(user1);
        rentalSystem.addUser(user2);


        // ---------------------------------------------------------
        // 3. Add vehicles to store inventory
        // ---------------------------------------------------------
        Vehicle v1 = new Vehicle("1", "DL1234", VehicleType.FOUR_WHEELER,1000);
//        v1.setDailyRentalCost(1100);

        Vehicle v2 = new Vehicle("2", "DL5678", VehicleType.FOUR_WHEELER,1200);
//        v2.setDailyRentalCost(1400);

        store1.getInventory().addVehicle(v1);
        store1.getInventory().addVehicle(v2);


        // ---------------------------------------------------------
        // 4. User selects store and searches vehicles
        // ---------------------------------------------------------
        RentalStore selectedStore = rentalSystem.getStore("1001");

        LocalDate fromDate = LocalDate.of(2025, 12, 5);
        LocalDate toDate   = LocalDate.of(2025, 12, 7);

        System.out.println("\nAvailable vehicles from " + fromDate + " to " + toDate + ":");

        for (Vehicle v : selectedStore.getVehicles(VehicleType.FOUR_WHEELER, fromDate, toDate)) {
            System.out.println(" - " + v.getResgistrationNum()+ ": " + v.getVehicleType());
        }


        // ---------------------------------------------------------
        // 5. User creates reservation
        // ---------------------------------------------------------
        System.out.println("\nCreating reservation...");

        Reservation reservation =
                selectedStore.createReservation(
                        "1",                // vehicle ID
                        user1,
                        fromDate,
                        toDate
                );

        System.out.println("Reservation created with ID: " + reservation.getId());


        // ---------------------------------------------------------
        // 6. User starts the trip
        // ---------------------------------------------------------
        System.out.println("\nStarting trip...");
        selectedStore.startTrip(reservation.getId());


        // ---------------------------------------------------------
        // 7. User submits the vehicle
        // ---------------------------------------------------------
        System.out.println("Submitting vehicle...");
        selectedStore.submitVehicle(reservation.getId());






        // ---------------------------------------------------------
        // 8. User makes payment
        // ---------------------------------------------------------
        System.out.println("\nProcessing Payment...");

        selectedStore.makePayment(new PayPalPaymentProcessor(),reservation.getTotalAmount());

    }

}