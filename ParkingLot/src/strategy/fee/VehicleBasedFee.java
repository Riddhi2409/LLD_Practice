package strategy.fee;

import entity.ParkingTicket;
import enums.VehicleSize;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class VehicleBasedFee implements FeeStrategy{

    private static final Map<VehicleSize,Double> HOURLY_RATES=Map.of(
            VehicleSize.LARGE,30.0,
            VehicleSize.MEDIUM,20.0,
            VehicleSize.SMALL,10.0
    );

    @Override
    public double calculateFee(ParkingTicket ticket) {
        long minutes= Duration.between(ticket.getStartTime(), ticket.getEndTime()).toMinutes() ;
        long hours= (long) Math.ceil(minutes/60.0);
        double fees=hours * HOURLY_RATES.get(ticket.getVehicle().getVehicleSize());
        return fees;
    }
}
