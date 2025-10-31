package dao;

import database.Database;
import models.Booking;

import java.sql.*;
import java.util.ArrayList;

public class BookingDAOImpl implements BookingDAO {
    @Override
    public boolean bookRoom(int room_id, int customer_id) throws SQLException {
        try(Connection conn = Database.getConnection()){
            try(PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Bookings WHERE room_id = ? AND customer_id = ?;")) {
                preparedStatement.setInt(1, room_id);
                preparedStatement.setInt(2, customer_id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    return false;
                }
            }
            try(PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Bookings (room_id, customer_id) VALUES (?, ?);")) {
                preparedStatement.setInt(1, room_id);
                preparedStatement.setInt(2, customer_id);
                int res = preparedStatement.executeUpdate();
                return true;
            }
        }
    }

    @Override
    public ArrayList<Booking> getAllBookings() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Booking> getBookingsByCustomerEmail(String email) throws SQLException {
        return null;
    }

    @Override
    public void cancelBooking(int booking_id) throws SQLException {

    }
}
