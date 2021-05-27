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

    @GetMapping({"/galleryPlayGround"})
    public String galleryPlayGround(@RequestParam(value = "sportName", required = false) String sportName, Model model) {

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
        return "galleryPlayGround";
    }
}

//
//    @GetMapping({"/selectPlayGround"})
//    public String selectPlayGround(@ModelAttribute("user") User user,
//                                   @RequestParam(value="sportName", required=false) String sportName,
//                                   @RequestParam(value="userID", required=false) String userID,
//                                   Model model) {
//
//        //************************************************************************
//
////        String sportName1 = "Football";
////		Long sportId1 = sportService.returnSportId(sportName1);
////        PlayGround playGround1 = new PlayGround("FootballField1","1 Feleacului Street, Feleac",sportId1,110);
////        playGroundService.register(playGround1);
////
////        String sportName2 = "Football";
////        Long sportId2 = sportService.returnSportId(sportName2);
////        PlayGround playGround2 = new PlayGround("FootballField2","2 Somesului Street, Cluj-Napoca",sportId2,120);
////        playGroundService.register(playGround2);
////
////        String sportName3 = "Football";
////        Long sportId3 = sportService.returnSportId(sportName3);
////        PlayGround playGround3 = new PlayGround("FootballField3","3 Turda Street, Turda",sportId3,130);
////        playGroundService.register(playGround3);
////
////        String sportName4 = "Football";
////        Long sportId4 = sportService.returnSportId(sportName4);
////        PlayGround playGround4 = new PlayGround("FootballField4","4 Gheorgheni Street, Cluj-Napoca",sportId4,140);
////        playGroundService.register(playGround4);
////
////        String sportName5 = "Football";
////        Long sportId5 = sportService.returnSportId(sportName5);
////        PlayGround playGround5 = new PlayGround("FootballField5","5 Floresti Street, Floresti",sportId5,150);
////        playGroundService.register(playGround5);
////
////
////        String sportName6 = "Tennis";
////        Long sportId6 = sportService.returnSportId(sportName6);
////        PlayGround playGround6 = new PlayGround("TennisField1","1 Feleacului Street, Feleac",sportId6,210);
////        playGroundService.register(playGround6);
////
////        String sportName7 = "Tennis";
////        Long sportId7 = sportService.returnSportId(sportName7);
////        PlayGround playGround7 = new PlayGround("TennisField2","2 Somesului Street, Cluj-Napoca",sportId7,220);
////        playGroundService.register(playGround7);
//
//
////        User foundUser = userRepo.findByUserEmail(user.getUserEmail());
////        user = foundUser;
//
//        model.addAttribute("userID", userID);
//
//        List<PlayGround> playGroundList = new ArrayList<>();
//        Iterable<PlayGround> iterable = playGroundService.findAll();
//        Iterator<PlayGround> iterator = iterable.iterator();
//        while(iterator.hasNext()) {
//            PlayGround element = iterator.next();
////            sportName = "Football";
//            if (sportName==null) {
//                playGroundList.add(element);
//            }
//            else{
//                Long sportId = sportService.returnSportId(sportName);
//                if (element.getSportId()==sportId) {
//                    playGroundList.add(element);
//                }
//            }
//        }
//
//        model.addAttribute("myPlayGroundList", playGroundList);
//        return "selectPlayGround";
//
//    }
//
//}
