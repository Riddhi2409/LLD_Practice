package org.example.vehicle;

import java.time.LocalDate;

public class DateInterval {
    private final LocalDate to;
    private final LocalDate from;

    public DateInterval(LocalDate from, LocalDate to) {
        if (to.isBefore(from)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    //comparing with booked.to.isBefore(other)
    public boolean overlaps(DateInterval other) {
        return this.from.isBefore(other.to) && this.to.isAfter(other.from);
    }


}
