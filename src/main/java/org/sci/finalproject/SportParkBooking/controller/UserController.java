package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.Booking;
import org.sci.finalproject.SportParkBooking.model.PlayGround;
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
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SportService sportService;
    @Autowired
    private PlayGroundService playGroundService;
    @Autowired
    private UserRepo userRepo;

    @GetMapping({"/loginOrRegister"})
    public String loginOrRegister(@RequestParam(value="playGroundName", required=false) String playGroundName,
                                  @RequestParam(value="sportName", required=false) String sportName,
                                  Model model) {
        User emptyUser = new User();
        model.addAttribute("user", emptyUser);
        model.addAttribute("playGroundName", playGroundName);
        model.addAttribute("sportName", sportName);
        return "loginOrRegister";
    }

    @RequestMapping("/register")
    public String myRegisterPage(@RequestParam(value="playGroundName", required=false) String playGroundName, Model model) {
        User emptyUser = new User();
        model.addAttribute("user", emptyUser);
        model.addAttribute("playGroundName", playGroundName);
        return "register";
    }

    @RequestMapping(value = "/registerUser")
    public String registerUser(@RequestParam(value="playGroundName", required=false) String playGroundName, @ModelAttribute("booking") Booking booking, @ModelAttribute("user") User user, BindingResult errors, Model model) {
        boolean registerResult = userService.register(user);
        model.addAttribute("playGroundName", playGroundName);
        model.addAttribute("userID", user.getUserID());
        if (registerResult) {
            return "selectBookingDate";
        } else {
            return "error";
        }
    }

    @RequestMapping("/login")
    public String myLoginPage(@RequestParam(value="playGroundName", required=false) String playGroundName, Model model) {
        User emptyUser = new User();
        model.addAttribute("user", emptyUser);
        model.addAttribute("playGroundName", playGroundName);
        return "login";
    }

    @RequestMapping(value = "/loginUser")
    public String loginUser(@RequestParam(value="playGroundName", required=false) String playGroundName, @ModelAttribute("booking") Booking booking, @ModelAttribute("user") User user, BindingResult errors, Model model) {
        boolean loginResult = userService.login(user);
        model.addAttribute("playGroundName", playGroundName);
        Long userID = userRepo.findByUserEmail(user.getUserEmail()).getUserID();
        model.addAttribute("userID", userID);
        if (loginResult) {
            return "selectBookingDate";
        } else {
            return "error";
        }
    }
}
