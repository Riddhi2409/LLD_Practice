package strategy.fee;

import entity.ParkingTicket;

import java.time.Duration;

public class FlatRateBasedFee implements FeeStrategy{

    private static final double RATE_PER_HOUR = 10.0;

    @Override
    public double calculateFee(ParkingTicket ticket) {
        long minutes= Duration.between(ticket.getStartTime(), ticket.getEndTime()).toMinutes();
        long hours=(long) Math.ceil(minutes/60.0);
        double fees=hours * RATE_PER_HOUR;
        return fees;
    }
}
