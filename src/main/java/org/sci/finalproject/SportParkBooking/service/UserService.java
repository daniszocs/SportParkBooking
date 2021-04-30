package org.sci.finalproject.SportParkBooking.service;

import org.sci.finalproject.SportParkBooking.model.User;
import org.sci.finalproject.SportParkBooking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void register(User user) {
        userRepo.save(user);
//        LOGGER.info("User has been registered");
    }


}
