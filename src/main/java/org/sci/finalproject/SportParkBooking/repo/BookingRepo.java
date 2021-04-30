package org.sci.finalproject.SportParkBooking.repo;

import org.sci.finalproject.SportParkBooking.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Long> {

}
