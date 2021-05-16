package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.Sport;
import org.sci.finalproject.SportParkBooking.repo.SportRepo;
import org.sci.finalproject.SportParkBooking.service.SportService;
import org.sci.finalproject.SportParkBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SportController {
    @Autowired
    private SportService sportService;
    private SportRepo sportRepo;

    @GetMapping({"/selectSport"})
    public String selectSport(Model model, @RequestParam(value="sportName", required=false) String sportName) {
        // http://localhost:8080/hello?name=maki
        // model = un camp din view/html

        //************************************************************************
        Sport sport1 = new Sport("Footbal");
        sportService.register(sport1);

        Sport sport2 = new Sport("Tennis");
        sportService.register(sport2);


        List<Sport> sportList = new ArrayList<>();
//        sportList.add(sport1);
//        sportList.add(sport2);

        Iterable<Sport> iterable = sportService.findAll();
        iterable.forEach(sportList::add);

        model.addAttribute("mySportList", sportList);
        return "selectSport";


    }
}
