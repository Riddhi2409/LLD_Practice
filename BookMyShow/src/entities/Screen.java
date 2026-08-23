package entities;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final String id;
    private final List<Seat> seats;

    public Screen(String id) {
        this.id = id;
        seats=new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void addSeat(Seat seat){
        seats.add(seat);
    }
}
