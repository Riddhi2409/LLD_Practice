package strategy;

public class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        // Process credit card payment
        // ...
        System.out.println("Credit card processed the amount: "+amount);
        return true;
    }
}
