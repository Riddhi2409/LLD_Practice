package org.example.bill;

import org.example.reservation.Reservation;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class BillingManager {

    private BillingStrategy billingStrategy;

    // store all bills here
    private final Map<Integer, Bill> bills = new ConcurrentHashMap<>();

    public BillingManager(BillingStrategy billingStrategy) {
        this.billingStrategy = billingStrategy;
    }

    public Bill generateBill(Reservation reservation) {

        Bill bill = billingStrategy.generateBill(reservation);
        bills.put(bill.getBillId(), bill);
        return bill;
    }

    public Optional<Bill> getBill(int billId) {
        return Optional.ofNullable(bills.get(billId));
    }

    public void setBillingStrategy(BillingStrategy billingStrategy) {
        this.billingStrategy = billingStrategy;
    }
}

