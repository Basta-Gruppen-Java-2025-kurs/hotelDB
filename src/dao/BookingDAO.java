package dao;

import models.Booking;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingDAO {
    boolean bookRoom(int room_id, int customer_id) throws SQLException;
    ArrayList<Booking> getAllBookings() throws SQLException;
    ArrayList<Booking> getBookingsByCustomerEmail(String email) throws SQLException;
    void cancelBooking(int booking_id) throws SQLException;
}
