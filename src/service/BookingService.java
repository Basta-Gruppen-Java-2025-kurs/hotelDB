package service;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import models.Booking;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookingService {
    private final BookingDAO bookingDAO = new BookingDAOImpl();

    public boolean bookRoom(int room_id, int customer_id) {
        try {
            boolean success = bookingDAO.bookRoom(room_id, customer_id);
            if (!success) {
                System.out.println("Room not available.");
            }
            return success;
        } catch (SQLException e) {
            System.out.println("Error booking a room: " + e);
            return false;
        }
    }

    public ArrayList<Booking> getAllBookings() {
        try {
            return bookingDAO.getAllBookings();
        } catch (SQLException e) {
            System.out.println("Error getting the list of bookings: " + e);
            return null;
        }
    }

    public ArrayList<Booking> getBookingsByCustomerEmail(String email) {
        try {
            return bookingDAO.getBookingsByCustomerEmail(email);
        } catch (SQLException e) {
            System.out.println("Error getting bookings by customer email: " + e);
            return null;
        }
    }

    public void cancelBooking(int booking_id) {
        try {
            bookingDAO.cancelBooking(booking_id);
            System.out.println("Booking #" + booking_id + " canceled.");
        } catch (SQLException e) {
            System.out.println("Error canceling the booking: " + e);
        }
    }

}
