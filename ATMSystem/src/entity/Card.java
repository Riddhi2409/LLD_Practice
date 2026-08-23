package entity;

public class Card {
    String cardNum;
    int pin;

    public String getCardNum() {
        return cardNum;
    }

    public int getPin() {
        return pin;
    }

    public Card(String cardNum, int pin) {
        this.cardNum = cardNum;
        this.pin = pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
