package org.sci.finalproject.SportParkBooking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Date;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long BookingID;
    private Date bookingDate;
    private Time bookingHour;
    /*keep int? or change to double ?? Possible values: 1, 1.5, 2 ?*/
//    private int bookingDuration;
    private int  bookingPrice;
    private long userID;
    private long playGroundID;
    private BookingStatusEnum bookingStatus;
    private String bookingSignature;

    public Booking() {
    }

//    public Booking(Date bookingDate, Time bookingHour, int bookingDuration, int bookingTotalPrice, long userID, long playGroundID, BookingStatusEnum bookingStatus) {
    public Booking(Date bookingDate, Time bookingHour, int bookingPrice, long userID, long playGroundID, BookingStatusEnum bookingStatus) {
            this.bookingDate = bookingDate;
        this.bookingHour = bookingHour;
//        this.bookingDuration = bookingDuration;
        this.bookingPrice = bookingPrice;
        this.userID = userID;
        this.playGroundID = playGroundID;
        this.bookingStatus = bookingStatus;

        DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        this.bookingSignature = df.format(bookingDate) + bookingHour + playGroundID;
    }

    public long getBookingID() {
        return BookingID;
    }

    public void setBookingID(long bookingID) {
        BookingID = bookingID;
    }

    public Date getBookingDate() {  return bookingDate;  }

    public void setBookingDate(Date bookingDate) {  this.bookingDate = bookingDate;  }

    public Time getBookingHour() { return bookingHour;  }

    public void setBookingHour(Time bookingHour) { this.bookingHour = bookingHour;  }

//    public int getBookingDuration() {  return bookingDuration;  }
//
//    public void setBookingDuration(int bookingDuration) { this.bookingDuration = bookingDuration; }

    public int getBookingTotalPrice() { return bookingPrice;  }

    public void setBookingTotalPrice(int bookingTotalPrice) { this.bookingPrice = bookingTotalPrice; }

    public long getUserID() {  return userID;  }

    public void setUserID(long userID) {  this.userID = userID;  }

    public long getPlayGroundID() { return playGroundID;  }

    public void setPlayGroundID(long playGroundID) { this.playGroundID = playGroundID; }

    public BookingStatusEnum getBookingStatus() { return bookingStatus;  }

    public void setBookingStatus(BookingStatusEnum bookingStatus) { this.bookingStatus = bookingStatus; }

    public String getBookingSignature() {
        return bookingSignature;
    }
}
