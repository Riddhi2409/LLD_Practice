package enums;

public enum SeatType {
    PREMIUM(400),
    RECLINER(500),
    REGULAR(300);

    private final double price;

    SeatType(int price) {
        this.price=price;
    }

    public double getPrice() {
        return price;
    }
}
