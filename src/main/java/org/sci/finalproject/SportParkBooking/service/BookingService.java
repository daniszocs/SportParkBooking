package org.sci.finalproject.SportParkBooking.service;

import org.sci.finalproject.SportParkBooking.model.Booking;
import org.sci.finalproject.SportParkBooking.model.BookingStatusEnum;
import org.sci.finalproject.SportParkBooking.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    public void saveNewBooking(Booking newBooking){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String bookingSignature = df.format(newBooking.getBookingDate()) + newBooking.getBookingHour() + newBooking.getPlayGroundID();
        newBooking.setBookingSignature(bookingSignature);

        Booking foundBookingSignature = bookingRepo.findByBookingSignature(newBooking.getBookingSignature());
        if (foundBookingSignature==null){
            bookingRepo.save(newBooking);
        }

//        LOGGER.info("User has been registered");
    }

    public void updateBookingStatus(BookingStatusEnum bookingStatus){}


}
