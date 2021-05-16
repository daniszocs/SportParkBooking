package org.sci.finalproject.SportParkBooking.service;

import org.sci.finalproject.SportParkBooking.model.Sport;
import org.sci.finalproject.SportParkBooking.repo.SportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportService {

    @Autowired
    private SportRepo sportRepo;

    public boolean register(Sport sport) {
        Sport foundSport = sportRepo.findBySportName(sport.getSportName());
        if (foundSport==null){
            sportRepo.save(sport);
            return true;
        }
        return false;
//        LOGGER.info("Sport has been registered");
    }

    public Long returnSportId(String sportName){
        return sportRepo.findBySportName(sportName).getSportID();
    }
}


