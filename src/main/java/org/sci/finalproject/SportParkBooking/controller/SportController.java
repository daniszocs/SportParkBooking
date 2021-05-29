package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.Sport;
import org.sci.finalproject.SportParkBooking.repo.UserRepo;
import org.sci.finalproject.SportParkBooking.service.PlayGroundService;
import org.sci.finalproject.SportParkBooking.service.SportService;
import org.sci.finalproject.SportParkBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String myIndexPage(Model model){

        List<Sport> sportList = new ArrayList<>();
        Iterable<Sport> iterableSport = sportService.findAll();
        iterableSport.forEach(sportList::add);
        model.addAttribute("mySportList", sportList);

        return "index";
    }

    @RequestMapping("/")
    public String myDefaultPage(Model model){

        List<Sport> sportList = new ArrayList<>();
        Iterable<Sport> iterableSport = sportService.findAll();
        iterableSport.forEach(sportList::add);
        model.addAttribute("mySportList", sportList);

        return "index";
    }
}
