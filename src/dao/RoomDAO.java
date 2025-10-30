package dao;

import models.Room;

import java.sql.SQLException;
import java.util.List;

public interface RoomDAO {
    void addRoom(Room room) throws SQLException;
    List<Room> getAllRooms() throws SQLException;
    List<Room> getAvailableRooms() throws SQLException;
    void updateRoomPrice(int roomId, double price) throws SQLException;
    void updateRoomType(int roomId, String type) throws SQLException;
}
