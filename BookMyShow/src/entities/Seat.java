package entities;

import enums.SeatStatus;
import enums.SeatType;

import java.util.concurrent.locks.ReentrantLock;

public class Seat {
    private final String id;
    private final int row;
    private final int col;
    private final SeatType seatType;
    private SeatStatus seatStatus;
    private final ReentrantLock lock=new ReentrantLock();

    public Seat(String id, int row, int col, SeatType type) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.seatType = type;
        this.seatStatus = SeatStatus.AVAILABLE;
    }


    public String getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }
}
