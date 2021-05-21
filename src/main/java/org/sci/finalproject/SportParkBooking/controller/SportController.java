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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SportController {
    @Autowired
    private SportService sportService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = "/selectSport", method = RequestMethod.POST)
    public String selectSport(@ModelAttribute("user") User user, BindingResult errors, Model model) {

        //************************************************************************
        Sport sport1 = new Sport("Football");
        sportService.register(sport1);

        Sport sport2 = new Sport("Tennis");
        sportService.register(sport2);


        List<Sport> sportList = new ArrayList<>();

        Iterable<Sport> iterable = sportService.findAll();
        iterable.forEach(sportList::add);

        model.addAttribute("mySportList", sportList);
        return "selectSport";

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
//            ModelAndView mv = new ModelAndView("redirect:/selectSport");
//            mv.addObject("userID",user.getUserID());
//            return mv;
////            return "selectSport";
//        } else {
//            return new ModelAndView("error");
////            return "error";
//        }

    }
}
