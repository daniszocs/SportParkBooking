package org.sci.finalproject.SportParkBooking.service;

import org.sci.finalproject.SportParkBooking.model.Sport;
import org.sci.finalproject.SportParkBooking.repo.SportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportService {
    @Autowired
    private SportRepo sportRepo;

    public void registerSport(Sport sport) {
        sportRepo.save(sport);
//        LOGGER.info("User has been registered");
    }

    public long returnSportId(String sportName){
        return sportRepo.findBySportID(sportName).getSportID();
    }
}


