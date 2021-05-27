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
            playGroundDescription= "Sus pe Feleac va oferim o oportunitate unică ! Fotbal în mijlocul naturii într-o locație deosebită !";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 110;
            PlayGround playGround1 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround1);

            sportName = "Football";
            playGroundName = "FootballField2";
            playGroundAddress= "2 Somesului Street, Cluj-Napoca";
            playGroundDescription= "Pentru o combinație perfectă de sport + ieșire în natură vă recomandăm terenul numărul 2 ! Situat pe malul Someșului la doi pași de Aeroportul Cluj-Napoca acest teren este accesibil tuturor celor aleg o mișcare plăcută împreună cu prietenii!";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 120;
            PlayGround playGround2 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround2);

            sportName = "Football";
            playGroundName = "FootballField3";
            playGroundAddress= "3 Turda Street, Turda";
            playGroundDescription= "Situat pe drumul care leagă Cluj-Napoca de Turda acest teren vă oferă posibilitatea de a face sport într-un loc cu adevărat de vis !";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 130;
            PlayGround playGround3 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround3);

            sportName = "Football";
            playGroundName = "FootballField4";
            playGroundAddress= "4 Gheorgheni Street, Cluj-Napoca";
            playGroundDescription= "Vă invităm să descoperiți noul nostru teren amenajat pe lacul Gheorgheni ! Rezervați din timp. Este una dintre cele mai cerute locații !";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 140;
            PlayGround playGround4 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround4);

            sportName = "Football";
            playGroundName = "FootballField5";
            playGroundAddress= "5 Floresti Street, Floresti";
            playGroundDescription= "Cel mai nou teren pe care il punem la dispoziția clienților noștri este și cel mai spectaculos ! Situat în zona Florești acest teren beneficiază de încălzire subterană astfel că fotbalul aici poate fi practicat indiferent de condițiile meteo !";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 150;
            PlayGround playGround5 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround5);


            sportName = "Tennis";
            playGroundName = "TennisField1";
            playGroundAddress= "1 Faget Street, Faget";
            playGroundDescription= "Terenul 1 este pentru relaxare, relaxare, relaxare ! Nici nu ar putea fi altfel dat fiind zona superbă care îl înconjoară ! Situat în zona Făgetul Clujului acest teren vă așteaptă și pe timp de noapte având o nocturnă ultra-modernă instalată recent !";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 210;
            PlayGround playGround6 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround6);

            sportName = "Tennis";
            playGroundName = "TennisField2";
            playGroundAddress= "2 Centru Street, Cluj-Napoca";
            playGroundDescription= "Situat chiar în inima orașului Cluj-Napoca la doi pași de centru, în cadrul unui club exclusivist terenul numărul 2 este pentru cei care vor mai mult decăt o simplă oră de sport !";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 220;
            PlayGround playGround7 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround7);

            sportName = "Tennis";
            playGroundName = "TennisField3";
            playGroundAddress= "3 Gara Street, Cluj-Napoca";
            playGroundDescription= "Amenajat în vechea gară a orașului Cluj acest teren vă invită la o porție de mișcare și de istorie în același timp !";
            sportId = sportService.returnSportId(sportName);
            pricePerHour = 230;
            PlayGround playGround8 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
            playGroundService.register(playGround8);
        }
}
