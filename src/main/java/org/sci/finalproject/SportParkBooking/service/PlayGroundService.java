package org.sci.finalproject.SportParkBooking.service;

import org.sci.finalproject.SportParkBooking.model.PlayGround;
import org.sci.finalproject.SportParkBooking.repo.PlayGroundRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayGroundService {

    @Autowired
    private PlayGroundRepo playGroundRepo;

    public void registerPlayGround(PlayGround playGround) {
        playGroundRepo.save(playGround);
//        LOGGER.info("User has been registered");
    }


}
