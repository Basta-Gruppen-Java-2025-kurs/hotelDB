package service;

import dao.RoomDAO;
import dao.RoomDAOImpl;
import models.Room;

import java.sql.SQLException;
import java.util.List;

public class RoomService {
    private RoomDAO roomDAO;

    public RoomService() {
        this.roomDAO = new RoomDAOImpl();
    }

    public void addRoom(Room room) {
        try {
            roomDAO.addRoom(room);
            System.out.println("Room added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding room: " + e.getMessage());
        }
    }

    public List<Room> getAllRooms() {
        try {
            return roomDAO.getAllRooms();
        } catch (SQLException e) {
            System.out.println("Error fetching rooms: " + e.getMessage());
            return List.of();
        }
    }

    public List<Room> getAvailableRooms() {
        try {
            return roomDAO.getAvailableRooms();
        } catch (SQLException e) {
            System.out.println("Error fetching available rooms: " + e.getMessage());
            return List.of();
        }
    }

    public void updateRoomPrice(int roomId, double price) {
        try {
            roomDAO.updateRoomPrice(roomId, price);
            System.out.println("Room price updated.");
        } catch (SQLException e) {
            System.out.println("Error updating price: " + e.getMessage());
        }
    }

    public void updateRoomType(int roomId, String type) {
        try {
            roomDAO.updateRoomType(roomId, type);
            System.out.println("Room type updated.");
        } catch (SQLException e) {
            System.out.println("Error updating room type: " + e.getMessage());
        }
    }
}
