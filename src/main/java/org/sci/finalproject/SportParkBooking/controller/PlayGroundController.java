package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.PlayGround;
import org.sci.finalproject.SportParkBooking.model.Sport;
import org.sci.finalproject.SportParkBooking.service.PlayGroundService;
import org.sci.finalproject.SportParkBooking.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping({"/selectPlayGround"})
    public String selectPlayGround(Model model, @RequestParam(value="sportName", required=false) String sportName) {

        //************************************************************************

        String sportName1 = "Football";
		Long sportId1 = sportService.returnSportId(sportName1);
        PlayGround playGround1 = new PlayGround("FootballField1",sportId1,100);
        playGroundService.register(playGround1);

        String sportName2 = "Tennis";
        Long sportId2 = sportService.returnSportId(sportName2);
        PlayGround playGround2 = new PlayGround("TennisField1",sportId2,150);
        playGroundService.register(playGround2);

        String sportName3 = "Football";
        Long sportId3 = sportService.returnSportId(sportName3);
        PlayGround playGround3 = new PlayGround("FootballField2",sportId3,100);
        playGroundService.register(playGround3);

        List<PlayGround> playGroundList = new ArrayList<>();
//        playGroundList.add(playGround1);
//        playGroundList.add(playGround2);
//        playGroundList.add(playGround3);

        Iterable<PlayGround> iterable = playGroundService.findAll();
        Iterator<PlayGround> iterator = iterable.iterator();

        while(iterator.hasNext()) {
            PlayGround element = iterator.next();
//            sportName = "Football";
            if (sportName==null) {
                playGroundList.add(element);
            }
            else{
                Long sportId = sportService.returnSportId(sportName);
                if (element.getSportId()==sportId) {
                    playGroundList.add(element);
                }
            }
        }

        model.addAttribute("myPlayGroundList", playGroundList);
        return "selectPlayGround";

    }

}