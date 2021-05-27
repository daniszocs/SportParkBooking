package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.Sport;
import org.sci.finalproject.SportParkBooking.model.User;
import org.sci.finalproject.SportParkBooking.repo.UserRepo;
import org.sci.finalproject.SportParkBooking.service.PlayGroundService;
import org.sci.finalproject.SportParkBooking.service.SportService;
import org.sci.finalproject.SportParkBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SportController {
    @Autowired
    private SportService sportService;
    @Autowired
    private PlayGroundService playGroundService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;



//    @RequestMapping("/selectSport")
//    public String selectSport(@ModelAttribute("user") User user, BindingResult errors,
//                              Model model) {
//
////        //************************************************************************
////        Sport sport1 = new Sport("Football");
////        sportService.register(sport1);
////
////        Sport sport2 = new Sport("Tennis");
////        sportService.register(sport2);
//
//        User foundUser = userRepo.findByUserEmail(user.getUserEmail());
//        user = foundUser;
//        Long userID = user.getUserID();
//        model.addAttribute("userID", userID);
//
//        List<Sport> sportList = new ArrayList<>();
//        Iterable<Sport> iterableSport = sportService.findAll();
//        iterableSport.forEach(sportList::add);
//        model.addAttribute("mySportList", sportList);
////
////        List<PlayGround> playGroundList = new ArrayList<>();
////        Iterable<PlayGround> iterablePLayGround = playGroundService.findAll();
////        iterablePLayGround.forEach(playGroundList::add);
////        model.addAttribute("myPlayGroundList", playGroundList);
////
////        List<User> userList = new ArrayList<>();
////        Iterable<User> iterableUser = userService.findAll();
////        iterableUser.forEach(userList::add);
////        model.addAttribute("myUserList", userList);
//
//        return "selectSport";
//    }
}
