package entity;

public class Item {
    String name;
    String code;
    int price;

    public Item(String name, String code, int price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }
}
