package org.example.payment;

import org.example.bill.Bill;

public interface PaymentStrategy {

    Payment processPayment(Bill bill, double paymentAmount);
}
