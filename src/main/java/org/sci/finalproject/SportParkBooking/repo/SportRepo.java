package org.sci.finalproject.SportParkBooking.repo;

import org.sci.finalproject.SportParkBooking.model.Sport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepo extends CrudRepository<Sport, Long> {

    public Sport findBySportName(String sportName);
    public Sport findBySportID(Long sportID);
}
