package dao;

import models.Room;

import java.util.List;

public interface RoomDAO {
    void addRoom(Room room) throws Exception;
    List<Room> getAllRooms() throws Exception;
    List<Room> getAvailableRooms() throws Exception;
    void updateRoomPrice(int roomId, double price) throws Exception;
    void updateRoomType(int roomId, String type) throws Exception;
}
