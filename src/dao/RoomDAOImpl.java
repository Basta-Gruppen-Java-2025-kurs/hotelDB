package dao;

import database.Database;
import models.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO{

    @Override
    public void addRoom(Room room) throws SQLException {
        String sql = "INSERT INTO Rooms (type, price, number, available) VALUES (?, ?, ?, ?)";

        try(Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, room.getType());
            statement.setDouble(2, room.getPrice());
            statement.setInt(3, room.getNumber());
            statement.setBoolean(4, room.isAvailable());

            statement.executeUpdate();

        }

    }

    @Override
    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Rooms";

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
    public List<Room> getAvailableRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Rooms WHERE available = true;";

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
    public void updateRoomPrice(int roomId, double price) throws SQLException {
        String sql = "UPDATE Rooms SET price = ? WHERE room_id = ?";

        try(Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, price);
            statement.setInt(2, roomId);

            statement.executeUpdate();
        }
    }

    @Override
    public void updateRoomType(int roomId, String type) throws SQLException {
        String sql = "UPDATE Rooms SET type = ? WHERE room_id = ?";

        try(Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, type);
            statement.setInt(2, roomId);

            statement.executeUpdate();
        }
    }
}
