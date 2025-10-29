package dao;

import models.Room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO{
    @Override
    public void addRoom(Room room) throws SQLException {
        String sql = "INSERT INTO rooms (type, price, number, available) VALUES (?, ?, ?, ?)";


    }

    @Override
    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sqlQuery = "SELECT * FROM rooms";

         try(Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sqlQuery)) {

             while (result.next()) {
                 Room room = new Room(
                         result.getString("type"),
                         result.getDouble("price"),
                         result.getInt("number")
                 );
                 room.setId(result.getInt("room_id"));
                 room.setAvailable(result.getBoolean("available"));
                 rooms.add(room);
             }
         }

        return rooms;
    }

    @Override
    public List<Room> getAvailableRooms() {
        return List.of();
    }

    @Override
    public void updateRoomPrice(int roomId, double price) {

    }

    @Override
    public void updateRoomType(int roomId, String type) {

    }
}
