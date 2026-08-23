package service;

import entities.*;
import enums.PaymentStatus;
import strategy.payment.PaymentStrategy;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookingManager {
    private final SeatLockManager seatLockManager;

    public BookingManager(SeatLockManager seatLockManager) {
        this.seatLockManager = seatLockManager;
    }

    Optional<Booking> createBooking(User user, Show show, List<Seat> seats, PaymentStrategy paymentStrategy){
        if(seatLockManager.lockSeats(show,seats, user.getId())) {
            // 2. Calculate the total price
            double totalAmount = show.getPricingStrategy().calculatePrice(seats);

            // 3. Process Payment
            Payment payment = paymentStrategy.pay(totalAmount);

            // 4. If payment is successful, create the booking
            if (payment.getStatus() == PaymentStatus.SUCCESS) {
                Booking booking = new Booking.BookingBuilder()
                        .setUser(user)
                        .setShow(show)
                        .setSeats(seats)
                        .setTotalAmount(totalAmount)
                        .setPayment(payment)
                        .setId(UUID.randomUUID().toString())
                        .build();

                // 5. Confirm the booking (mark seats as BOOKED)
                booking.confirmBooking();

                // Clean up the lock map
                seatLockManager.unlockSeats(show, seats, user.getId());

                return Optional.of(booking);

            }
        }
        System.out.println("Payment failed. Please try again.");
        return Optional.empty();

    }

}
