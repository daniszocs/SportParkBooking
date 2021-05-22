package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.Sport;
import org.sci.finalproject.SportParkBooking.model.User;
import org.sci.finalproject.SportParkBooking.repo.UserRepo;
import org.sci.finalproject.SportParkBooking.service.SportService;
import org.sci.finalproject.SportParkBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SportService sportService;
    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/home")
    public String myIndexPage(@ModelAttribute("user") User user,
                              @RequestParam(value="userID", required=false) String userID,
                              @RequestParam(value="userEmail", required=false) String userEmail,
                              Model model) {
//        if (userID != "" && userID != null) {
//            model.addAttribute("userID", userID);
//            return "index";
//        }
//        if (userEmail != "" && userEmail != null) {
//            User foundUser = userRepo.findByUserEmail(userEmail);
//            model.addAttribute("userID", foundUser.getUserID());
//            return "index";
//        }
        return "index";
    }

    @RequestMapping("/")
    public String myDefaultPage(@ModelAttribute("user") User user,
                                @RequestParam(value="userID", required=false) String userID,
                                @RequestParam(value="userEmail", required=false) String userEmail,
                                Model model) {
//        if (userID != "" && userID != null) {
//            model.addAttribute("userID", userID);
//            return "index";
//        }
//        if (userEmail != "" && userEmail != null) {
//            User foundUser = userRepo.findByUserEmail(userEmail);
//            model.addAttribute("userID", foundUser.getUserID());
//            return "index";
//        }
        return "index";
    }

    @RequestMapping("/register")
    public String myRegisterPage(Model model) {
        User emptyUser = new User();
        model.addAttribute("user", emptyUser);
        return "register";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user, BindingResult errors, Model model) {
        boolean registerResult = userService.register(user);
        if (registerResult) {
            return "login";
        } else {
            return "error";
        }
    }

    @RequestMapping("/login")
    public String myLoginPage(Model model) {
        User emptyUser = new User();
        model.addAttribute("user", emptyUser);
        return "login";
    }

//    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
//    public String loginUser(@ModelAttribute("user") User user, BindingResult errors, Model model) {
//        boolean loginResult = userService.login(user);
//        if (loginResult) {
//            User foundUser = userRepo.findByUserEmail(user.getUserEmail());
//            user = foundUser;
//
//            List<Sport> sportList = new ArrayList<>();
//            Iterable<Sport> iterable = sportService.findAll();
//            iterable.forEach(sportList::add);
//            model.addAttribute("mySportList", sportList);
//
////            ModelAndView mv = new ModelAndView("redirect:/selectSport");
////            mv.addObject("userID",user.getUserID());
////            return mv;
//            return "selectSport";
//        } else {
////            return new ModelAndView("error");
//            return "error";
//        }
//    }

}
