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
    private PlayGroundService playGroundService;
    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/home")
    public String myIndexPage(Model model){

        //initialize database with Sport and PlayGround values;
        initIndex();

        List<Sport> sportList = new ArrayList<>();
        Iterable<Sport> iterableSport = sportService.findAll();
        iterableSport.forEach(sportList::add);
        model.addAttribute("mySportList", sportList);

        return "index";
    }

    @RequestMapping("/")
    public String myDefaultPage(Model model){

        //initialize database with Sport and PlayGround values;
        initIndex();

        List<Sport> sportList = new ArrayList<>();
        Iterable<Sport> iterableSport = sportService.findAll();
        iterableSport.forEach(sportList::add);
        model.addAttribute("mySportList", sportList);

        return "index";
    }

//    @RequestMapping("/home")
//    public String myIndexPage(@ModelAttribute("user") User user,
//                              @RequestParam(value="userID", required=false) String userID,
//                              @RequestParam(value="userEmail", required=false) String userEmail,
//                              Model model) {
////        if (userID != "" && userID != null) {
////            model.addAttribute("userID", userID);
////            return "index";
////        }
////        if (userEmail != "" && userEmail != null) {
////            User foundUser = userRepo.findByUserEmail(userEmail);
////            model.addAttribute("userID", foundUser.getUserID());
////            return "index";
////        }
//        return "index";
//    }

//    @RequestMapping("/")
//    public String myDefaultPage(@ModelAttribute("user") User user,
//                                @RequestParam(value="userID", required=false) String userID,
//                                @RequestParam(value="userEmail", required=false) String userEmail,
//                                Model model) {
////        if (userID != "" && userID != null) {
////            model.addAttribute("userID", userID);
////            return "index";
////        }
////        if (userEmail != "" && userEmail != null) {
////            User foundUser = userRepo.findByUserEmail(userEmail);
////            model.addAttribute("userID", foundUser.getUserID());
////            return "index";
////        }
//        return "index";
//    }

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

    @RequestMapping("/login")
    public String myLoginPage(@RequestParam(value="playGroundName", required=false) String playGroundName, Model model) {
        User emptyUser = new User();
        model.addAttribute("user", emptyUser);
        model.addAttribute("playGroundName", playGroundName);
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
        public void initIndex(){
            //************************************************************************
            Sport sport1 = new Sport("Football");
            sportService.register(sport1);

            Sport sport2 = new Sport("Tennis");
            sportService.register(sport2);

            //************************************************************************
            String sportName;
            String playGroundName;
            String playGroundAddress;
            String playGroundDescription;
            Long sportId;
            int pricePerHour;

            sportName = "Football";
            playGroundName = "FootballField1";
            playGroundAddress= "1 Feleacului Street, Feleac";
            playGroundDescription= "High up on Feleac we offer you a unique opportunity! Football in the middle of nature in a special location!";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 110;
            PlayGround playGround1 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround1);

            sportName = "Football";
            playGroundName = "FootballField2";
            playGroundAddress= "2 Somesului Street, Cluj-Napoca";
            playGroundDescription= "For a perfect combination of sports + nature outing, we recommend this playground! Located on the banks of the Someș, a stone's throw from Cluj-Napoca Airport, this playground is accessible to all those who choose a pleasant exercise with friends!";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 120;
            PlayGround playGround2 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround2);

            sportName = "Football";
            playGroundName = "FootballField3";
            playGroundAddress= "3 Turda Street, Turda";
            playGroundDescription= "Located on the road that connects Cluj-Napoca to Turda, this playground offers you the opportunity to do sports in a truly nice place!";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 130;
            PlayGround playGround3 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround3);

            sportName = "Football";
            playGroundName = "FootballField4";
            playGroundAddress= "4 Gheorgheni Street, Cluj-Napoca";
            playGroundDescription= "We invite you to discover our new playground near Lake Gheorgheni! Book in advance. It is one of the most requested locations!";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 140;
            PlayGround playGround4 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround4);

            sportName = "Football";
            playGroundName = "FootballField5";
            playGroundAddress= "5 Floresti Street, Floresti";
            playGroundDescription= "The newest playground for our customers is also the most spectacular! Located in the Floresti area, this playground benefits from underground heating so football can be played here regardless of the weather conditions!";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 150;
            PlayGround playGround5 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround5);


            sportName = "Tennis";
            playGroundName = "TennisField1";
            playGroundAddress= "1 Faget Street, Faget";
            playGroundDescription= "This playground is for relaxation, relaxation, relaxation! It couldn't be otherwise given the beautiful area that surrounds it! Located in the Făget Cluj area, this playground is waiting for you at night with recently installed ultra-modern lights!";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 210;
            PlayGround playGround6 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround6);

            sportName = "Tennis";
            playGroundName = "TennisField2";
            playGroundAddress= "2 Centru Street, Cluj-Napoca";
            playGroundDescription= "Located right in the heart of Cluj-Napoca, two steps from the center, in an exclusive playground, for those who want more from a than just an hour of sports!";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 220;
            PlayGround playGround7 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround7);

            sportName = "Tennis";
            playGroundName = "TennisField3";
            playGroundAddress= "3 Gara Street, Cluj-Napoca";
            playGroundDescription= "Situated in the old train station of Cluj, this playground invites you to take part in movement and history at the same time!";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 230;
            PlayGround playGround8 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround8);
        }
}
