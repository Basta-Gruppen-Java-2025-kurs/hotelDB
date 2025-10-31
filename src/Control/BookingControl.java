package Control;

import Helpers.IMenu;
import Helpers.MenuBuilder;
import Helpers.SafeInput;
import Helpers.TextMenu;
import models.Booking;
import service.BookingService;
import service.RoomService;

import java.util.ArrayList;
import java.util.Scanner;

public class BookingControl implements IMenu {
    private final BookingService bookingService = new BookingService();
    private final SafeInput si = new SafeInput(new Scanner(System.in));

    @Override
    public void menu() {
        MenuBuilder bookingMenu = new MenuBuilder("\033[1mBooking\033[22m menu", "Back");
        bookingMenu.addItem("Book a room", this::bookRoom)
                   .addItem("Show all bookings", this::getAllBookings)
                   .addItem("Find all bookings by customer email", this::getBookingsByCustomerEmail)
                   .addItem("Cancel a booking", this::cancelBooking);
        bookingMenu.runMenu();
    }

    void bookRoom() {
        TextMenu.listMenuLoop("Select room to book:", "Cancel", "No rooms found", new RoomService()::getAllRooms, room -> {
            int customerId = si.nextInt("Enter customer Id please: ");
            if (bookingService.bookRoom(room.getId(), customerId)) {
                System.out.println("Room " + room.getNumber() + " booked for customer #" + customerId);
            } else {
                System.out.println("Failed to book room " + room.getNumber() + " for customer #" + customerId);
            }
        }, true);
    }

    void getAllBookings() {
        ArrayList<Booking> bookings = bookingService.getAllBookings();
        System.out.println(Booking.tableHeader());
        for (Booking booking: bookings) {
            System.out.println(booking);
        }
    }

    void getBookingsByCustomerEmail() {
        String email = si.nextLine("Enter customer email: ").trim();
        ArrayList<Booking> bookings = bookingService.getBookingsByCustomerEmail(email);
        if (bookings.isEmpty()) {
            System.out.println("No bookings found for this email");
        } else {
            System.out.println(Booking.tableHeader());
            for (Booking booking: bookings) {
                System.out.println(booking);
            }
        }
    }

    void cancelBooking() {
        TextMenu.listMenuLoop("Select a booking to cancel:", "Back", "No bookings found.", bookingService::getAllBookings, booking -> {
            int bookingId = booking.getBooking_id();
            bookingService.cancelBooking(bookingId);
        }, true);
    }
}
