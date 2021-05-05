package org.sci.finalproject.SportParkBooking.service;

import org.sci.finalproject.SportParkBooking.model.Booking;
import org.sci.finalproject.SportParkBooking.model.BookingStatusEnum;
import org.sci.finalproject.SportParkBooking.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    public void saveNewBooking(Booking newBooking) {

        bookingRepo.save(newBooking);
//        LOGGER.info("User has been registered");
    }

    public void updateBookingStatus(BookingStatusEnum bookingStatus){}
}
