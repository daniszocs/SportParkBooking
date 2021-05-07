package org.sci.finalproject.SportParkBooking.repo;

import org.sci.finalproject.SportParkBooking.model.Booking;
import org.sci.finalproject.SportParkBooking.model.BookingStatusEnum;
import org.sci.finalproject.SportParkBooking.model.PlayGround;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.sql.Date;
import java.sql.Time;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Long> {

    public Booking findByBookingSignature(String bookingSignature);

}
