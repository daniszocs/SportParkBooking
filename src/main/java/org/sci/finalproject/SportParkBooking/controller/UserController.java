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
    public String myIndexPage() {
        return "index";
    }

    @RequestMapping("/")
    public String myDefaultPage() {
        return this.myIndexPage();
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
//            List<Sport> sportList = new ArrayList<>();
//            Iterable<Sport> iterable = sportService.findAll();
//            iterable.forEach(sportList::add);
//            model.addAttribute("mySportList", sportList);
            return "login";
        } else {
            return "error";
        }
    }
//
//    @GetMapping({"/confirmRegister"})
//    public String confirmRegister(Model model, @RequestParam(value="name", required=false) String name) {
//        model.addAttribute("name", name);
//        return "confirmRegistration";
//    }


    @RequestMapping("/login")
    public String myLoginPage(Model model) {
        User emptyUser = new User();
        model.addAttribute("user", emptyUser);
        return "login";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public ModelAndView loginUser(@ModelAttribute("user") User user, BindingResult errors, Model model) {
        boolean loginResult = userService.login(user);
        if (loginResult) {
            User foundUser = userRepo.findByUserEmail(user.getUserEmail());
            user = foundUser;

            List<Sport> sportList = new ArrayList<>();
            Iterable<Sport> iterable = sportService.findAll();
            iterable.forEach(sportList::add);
            model.addAttribute("mySportList", sportList);

            ModelAndView mv = new ModelAndView("redirect:/selectSport");
            mv.addObject("userID",user.getUserID());
            return mv;
        } else {
            return new ModelAndView("error");
        }
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
//            return "booking";
//        } else {
//            return "error";
//        }
//    }
//
//    @GetMapping({"/confirmLogin"})
//    public String confirmLogin(Model model, @RequestParam(value="name", required=false) String name) {
//        model.addAttribute("name", name);
//        return "confirmationLogin";
//    }



//    @GetMapping({"/hello"})
//    public String hello(Model model, @RequestParam(value="name", required=false) String name) {
//        // http://localhost:8080/hello?name=maki
//        // model = un camp din view/html
//        model.addAttribute("name", name);
//        return "index";
//    }
//
//
//    @GetMapping({"/test"})
//    public String test(Model model, @RequestParam(value="name", required=false) String name) {
//        // http://localhost:8080/hello?name=maki
//        // model = un camp din view/html
//
//        //************************************************************************
//        User user1 = new User("Ioan Farcas", "nelutufarcas", "eMailAddress@nelutu.ro", "0763532299", "parola.nelutu");
//        User user2 = new User("Daniel Szocs", "daniszocs", "eMailAddress@dani.ro", "0755001122", "parola.dani");
//        User user3 = new User("Luminita Daraban", "lumidaraban", "eMailAddress@luminita.ro", "0743201345", "parola.luminita");
//        User user4 = new User("Alex Blidar", "alexblidar", "eMailAddress@alex.ro", "0712212121", "parola.alex");
//        User user5 = new User("Florina Morari", "florinamorari", "eMailAddress@florina.ro", "0701100110", "parola.florina");
//
//
//        userService.register(user1);
//        userService.register(user2);
//        userService.register(user3);
//        userService.register(user4);
//        userService.register(user5);
//
//
//        List<User> usersList = new ArrayList<>();
//        usersList.add(user1);
//        usersList.add(user2);
//        usersList.add(user3);
//        usersList.add(user4);
//        usersList.add(user5);
//
//        model.addAttribute("myUsersList", usersList);
//        return "index";
//
//
//    }

}
