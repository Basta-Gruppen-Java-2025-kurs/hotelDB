package models;

public class Room {
    private int id;
    private String type;
    private double price;
    private int number;
    private boolean available;

    public Room(String type, double price, int number) {
        this.type = type;
        this.price = price;
        this.number = number;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format(
                "| %-3d | %-10s | %-7.2f | %-5d | %-9s |",
                id,
                type,
                price,
                number,
                available ? "Yes" : "No"
        );
    }
}
