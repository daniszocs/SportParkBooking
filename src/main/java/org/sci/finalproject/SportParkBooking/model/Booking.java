package org.sci.finalproject.SportParkBooking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long BookingID;
    private Date bookingDate;
    private Date bookingHour;
    /*keep int? or change to double ?? Possible values: 1, 1.5, 2 ?*/
    private int  bookingDuration;
    private int  bookingTotalPrice;
    private long userID;
    private long playGroundID;
    private BookingStatusEnum bookingStatus;

    public Booking() {
    }

    public Booking(String bookingDate, String bookingHour, int bookingDuration, int bookingTotalPrice,
                   long userID, long playGroundID, BookingStatusEnum bookingStatus) {
        try {
            String fullDate = bookingDate + " " + bookingHour;
            this.bookingDate = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(fullDate/*bookingDate*/);
            this.bookingHour = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(fullDate/*bookingHour*/);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.bookingDuration = bookingDuration;
        this.bookingTotalPrice = bookingTotalPrice;
        this.userID = userID;
        this.playGroundID = playGroundID;
        this.bookingStatus = bookingStatus;
    }

    public long getBookingID() {
        return BookingID;
    }

    public void setBookingID(long bookingID) {
        BookingID = bookingID;
    }

    public Date getBookingDate() {
        return bookingDate;
        /*When used: extract "dd/MM/yyyy" from bookingDate and stored to a String var...*/
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getBookingHour() {
        return bookingHour;
        /*When used: extract "hh:mm" from bookingHour and stored to a String var...*/
    }

    public void setBookingHour(Date bookingHour) {
        this.bookingHour = bookingHour;
    }

    public int getBookingDuration() {
        return bookingDuration;
    }

    public void setBookingDuration(int bookingDuration) {
        this.bookingDuration = bookingDuration;
    }

    public int getBookingTotalPrice() {
        return bookingTotalPrice;
    }

    public void setBookingTotalPrice(int bookingTotalPrice) {
        this.bookingTotalPrice = bookingTotalPrice;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getPlayGroundID() {
        return playGroundID;
    }

    public void setPlayGroundID(long playGroundID) {
        this.playGroundID = playGroundID;
    }

    public BookingStatusEnum getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatusEnum bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
