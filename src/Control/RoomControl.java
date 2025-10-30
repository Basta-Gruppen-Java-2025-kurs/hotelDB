package Control;

import Helpers.IMenu;
import Helpers.MenuBuilder;
import Helpers.SafeInput;
import models.Room;
import service.RoomService;

import java.util.List;
import java.util.Scanner;

public class RoomControl implements IMenu {
    private RoomService roomService = new RoomService();
    private Scanner scanner = new Scanner(System.in);
    private SafeInput safeInput = new SafeInput(scanner);

    @Override
    public void menu() {
        MenuBuilder roomMenu = new MenuBuilder("\n=== Room Menu ===", "Back to main menu");
        roomMenu.addItem("Add new room", this::addRoom)
                .addItem("Show all rooms", this::showAllRooms)
                .addItem("Show available rooms", this::showAvailableRooms)
                .addItem("Update room price", this::updateRoomPrice)
                .addItem("Change room type", this::updateRoomType);
        roomMenu.runMenu();
    }

    private void addRoom() {
        String type = safeInput.nextLine("Enter room type:");
        double price = safeInput.nextDouble("Enter room price:");
        int number = safeInput.nextInt("Enter room number:");

        Room room = new Room(type, price, number);
        roomService.addRoom(room);
    }

    private void showAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        printRoomTable(rooms);
    }

    private void showAvailableRooms() {
        List<Room> rooms = roomService.getAvailableRooms();
        printRoomTable(rooms);
    }

    private void updateRoomPrice() {
        List<Room> rooms = roomService.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        printRoomTable(rooms);

        int id = selectRoomIdFromList(rooms);
        double price = safeInput.nextDouble("Enter new price:");
        roomService.updateRoomPrice(id, price);

        System.out.println("Price updated successfully!");
    }

    private void updateRoomType() {
        List<Room> rooms = roomService.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        printRoomTable(rooms);

        int id = safeInput.nextInt("Enter room ID to update:");
        String type = safeInput.nextLine("Enter new room type:");
        roomService.updateRoomType(id, type);

        System.out.println("Room type is updated successfully!");
    }

    private void printRoomTable(List<Room> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
            return;
        }
        System.out.println("| ID  | Type       | Price   | Num  | Available |");
        System.out.println("|-----|------------|---------|------|-----------|");
        rooms.forEach(System.out::println);
    }

    private int selectRoomIdFromList(List<Room> rooms) {
        int id;
        while (true) {
            id = safeInput.nextInt("Enter room ID from the list:");
            int finalId = id;
            boolean exists = rooms.stream().anyMatch(r -> r.getId() == finalId);
            if (exists) break;
            System.out.println("Invalid room ID. Try again.");
        }
        return id;
    }
}
