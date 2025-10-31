package models;

import Helpers.Named;
import Helpers.TableFormatter;
import service.BookingService;

import java.sql.Date;

public class Booking implements Named {
    private int booking_id,
        room_id,
        customer_id;
    private Date start_date,
         end_date;
    private int roomNumber;
    private String customerName;

    private final BookingService bookingService = new BookingService();

    private static final TableFormatter tf = new TableFormatter();
    static {
        tf.setBoldHeader(true).setTitle("Bookings list").setTabs(10,50,10);
    }

    Booking(int booking_id, int room_id, int customer_id, int roomNumber, String customerName) {
        this.booking_id = booking_id;
        this.customer_id = customer_id;
        this.room_id = room_id;
        this.roomNumber = roomNumber;
        this.customerName = customerName;
    }

    Booking(int booking_id, int room_id, int customer_id, int roomNumber, String customerName, Date start_date, Date end_date) {
        this.booking_id = booking_id;
        this.customer_id = customer_id;
        this.room_id = room_id;
        this.roomNumber = roomNumber;
        this.customerName = customerName;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public static String tableHeader() {
        return tf.formatHeader("Booking id", "Customer name", "Room number") + "\n" +tf.formatSeparator();
    }

    @Override
    public String toString() {
        return tf.formatNextRow("" + booking_id, customerName, "" + roomNumber);
    }

    @Override
    public String getName() {
        return booking_id + ": " + customerName + ", room " + roomNumber;
    }

    //region getters & setters
    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    //endregion
}
