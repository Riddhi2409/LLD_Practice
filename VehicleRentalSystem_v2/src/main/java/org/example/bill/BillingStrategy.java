package org.example.bill;

import org.example.reservation.Reservation;

public interface BillingStrategy {

    Bill generateBill(Reservation reservation);
}