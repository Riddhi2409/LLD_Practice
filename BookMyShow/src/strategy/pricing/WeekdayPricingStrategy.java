package strategy.pricing;

import entities.Seat;

import java.util.List;

public class WeekdayPricingStrategy implements PricingStrategy{
    @Override
    public double calculatePrice(List<Seat> seats) {
        return seats.stream().mapToDouble(seat-> seat.getSeatType().getPrice()).sum();
    }
}
