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
        User foundUser = userRepo.findByUserName(user.getUserName());
        if (foundUser==null){
        userRepo.save(user);
        }
//        LOGGER.info("User has been registered");
    }

    public boolean login(User user) {
        User loginUser = userRepo.findByUserEmail(user.getUserEmail());
        if (loginUser == null) {
            //LOGGER.info("User has not been found");
            return false;
        }
        if (user.getUserPassword().equals(loginUser.getUserPassword())) {
            //LOGGER.info("User has been logged in");
            return true;
        }
        //LOGGER.error("Should not get here");
        return false;
    }

    public Long returnUserID(String userName){
        return userRepo.findByUserName(userName).getUserID();
    }

//    public boolean loginUser(User user){
//        User loginUser = userRepo.findByUserName(user.getUserName());
////        userRepo.save(user);
//        if(loginUser == null){
//            return false;
//
//        }
//        if (user.getUserPassword().equals(loginUser.getUserPassword())){
//
//            return true;
//        }
//
//        return false;
//
//    }
//    //LOGGER.error("User has not been found");

    public boolean updateUser(User oldUser, User newUser) {
        User foundUser = userRepo.findByUserName(oldUser.getUserName());
        if (newUser.getUserName().equals(foundUser.getUserName())) {
            userRepo.delete(oldUser);
            userRepo.save(newUser);
            return true;
        }
        return false;
    }

    public void deleteAccount(User userToDelete){
        userRepo.delete(userToDelete);

    }

}
