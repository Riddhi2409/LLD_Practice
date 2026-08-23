package strategy.fee;

import entity.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket ticket);
}
