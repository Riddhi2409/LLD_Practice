package strategy;

public class PayPalPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        // Process PayPal payment
        // ...
        System.out.println("PayPal processed the amount: " + amount );
        return true;
    }
}
