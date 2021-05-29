package org.sci.finalproject.SportParkBooking.service;

import org.sci.finalproject.SportParkBooking.model.User;
import org.sci.finalproject.SportParkBooking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public boolean register(User user) {
        User foundUser = userRepo.findByUserName(user.getUserName());
        if (foundUser==null){
            userRepo.save(user);
            return true;
        }
        return false;
    }

    public Iterable<User> findAll() { return userRepo.findAll(); }

    public boolean login(User user) {
        User foundUser = userRepo.findByUserEmail(user.getUserEmail());
        if (foundUser == null) {
            //User has not been found
            return false;
        }
        if (user.getUserPassword().equals(foundUser.getUserPassword())) {
            //User has been logged in
            return true;
        }
        //Should not get here
        return false;
    }

    public boolean updateUser(User oldUser, User newUser) {
        User foundUser = userRepo.findByUserEmail(oldUser.getUserEmail());
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

    public Long returnUserID(String userName){
        return userRepo.findByUserName(userName).getUserID();
    }
}
