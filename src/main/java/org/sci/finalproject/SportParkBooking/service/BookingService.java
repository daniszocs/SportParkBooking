package org.sci.finalproject.SportParkBooking.service;

import org.sci.finalproject.SportParkBooking.model.Booking;
import org.sci.finalproject.SportParkBooking.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    public void saveNewBooking(Booking newBooking) {

        /*newBooking must pe configured before call of saveNewBooking call
         (IDs, totalPrice based on logged user and chosen playGround*/
        bookingRepo.save(newBooking);
//        LOGGER.info("User has been registered");
    }
}
