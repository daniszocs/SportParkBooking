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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/home")
    public String myIndexPage(@RequestParam(value="userID", required=false) String userID, @ModelAttribute("user") User user, Model model){

        List<Sport> sportList = new ArrayList<>();
        Iterable<Sport> iterableSport = sportService.findAll();
        iterableSport.forEach(sportList::add);
        model.addAttribute("mySportList", sportList);

        if (user.getUserEmail()!=null) {
            user.setUserID(userRepo.findByUserEmail(user.getUserEmail()).getUserID());
        }
        model.addAttribute("userID", userID);

        return "index";
    }

    @RequestMapping("/")
    public String myDefaultPage(@RequestParam(value="userID", required=false) String userID, @ModelAttribute("user") User user, Model model){

        List<Sport> sportList = new ArrayList<>();
        Iterable<Sport> iterableSport = sportService.findAll();
        iterableSport.forEach(sportList::add);
        model.addAttribute("mySportList", sportList);

        if (user.getUserEmail()!=null) {
            user.setUserID(userRepo.findByUserEmail(user.getUserEmail()).getUserID());
        }
        model.addAttribute("userID", userID);

        return "index";
    }
}
