package org.sci.finalproject.SportParkBooking.repo;

import org.sci.finalproject.SportParkBooking.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Long> {

    public Booking findByBookingSignature(String bookingSignature);
    public Booking findByBookingID(Long bookingID);
    public Booking findByUserID(Long userID);

}
