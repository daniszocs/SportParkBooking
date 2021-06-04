package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.PlayGround;
import org.sci.finalproject.SportParkBooking.model.User;
import org.sci.finalproject.SportParkBooking.repo.UserRepo;
import org.sci.finalproject.SportParkBooking.service.PlayGroundService;
import org.sci.finalproject.SportParkBooking.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class PlayGroundController {
    @Autowired
    private PlayGroundService playGroundService;
    @Autowired
    private SportService sportService;
    @Autowired
    private UserRepo userRepo;

    @GetMapping({"/selectPlayGround"})
    public String selectPlayGround(@RequestParam(value="userID", required=false) String userID, @RequestParam(value = "sportName", required = false) String sportName, Model model) {

        List<PlayGround> playGroundList = new ArrayList<>();
        Iterable<PlayGround> iterable = playGroundService.findAll();
        Iterator<PlayGround> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            PlayGround element = iterator.next();
//            sportName = "Football";
            if (sportName == null) {
                playGroundList.add(element);
            } else {
                Long sportId = sportService.returnSportId(sportName);
                if (element.getSportId() == sportId) {
                    playGroundList.add(element);
                }
            }
        }

        model.addAttribute("myPlayGroundList", playGroundList);
        model.addAttribute("sportName", sportName);
        model.addAttribute("userID", userID);
        return "selectPlayGround";
    }
}
