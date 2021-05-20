package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.Sport;
import org.sci.finalproject.SportParkBooking.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SportController {
    @Autowired
    private SportService sportService;

    @GetMapping({"/booking"})
    public String selectSport(Model model) {

        //************************************************************************
        Sport sport1 = new Sport("Football");
        sportService.register(sport1);

        Sport sport2 = new Sport("Tennis");
        sportService.register(sport2);


        List<Sport> sportList = new ArrayList<>();
//        sportList.add(sport1);
//        sportList.add(sport2);

        Iterable<Sport> iterable = sportService.findAll();
        iterable.forEach(sportList::add);

        model.addAttribute("mySportList", sportList);
        return "booking";


    }
}
