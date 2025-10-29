package main.entity;

public class Room {

    private int roomId;
    private String type;
    private double price;
    private int number;
    private boolean available;

    public Room(int roomId, String type, double price, int number, boolean available) {
        this.roomId = roomId;
        this.type = type;
        this.price = price;
        this.number = number;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", available=" + available +
                '}';
    }
}