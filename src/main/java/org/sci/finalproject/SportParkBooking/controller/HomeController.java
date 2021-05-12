package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.User;
import org.sci.finalproject.SportParkBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String myIndexPage() {
        return "index";
    }

    @RequestMapping("/register")
    public String myRegisterPage(Model model) {
        User emptyUser = new User();
        model.addAttribute("user", emptyUser);
        return "register";
    }


    @RequestMapping("/login")
    public String myLoginPage(Model model) {
        User emptyUser = new User();
        model.addAttribute("user", emptyUser);
        return "login";
    }


    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("user") User user, BindingResult errors, Model model) {
        // logic to process input data
        boolean loginResult = userService.login(user);
        if (loginResult) {
            return hello(model, user.getUserEmail());
        } else {
            return "error";
        }
    }

    @RequestMapping("/")
    public String myDefaultPage() {
        return this.myIndexPage();
    }


    @GetMapping({"/hello"})
    public String hello(Model model, @RequestParam(value="name", required=false) String name) {
        // http://localhost:8080/hello?name=maki
        // model = un camp din view/html
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping({"/test"})
    public String test(Model model, @RequestParam(value="name", required=false) String name) {
        // http://localhost:8080/hello?name=maki
        // model = un camp din view/html

        //************************************************************************
        User user1 = new User("Ioan Farcas", "nelutufarcas", "eMailAddress@nelutu.ro", "0763532299", "parola.nelutu");
        User user2 = new User("Daniel Szocs", "daniszocs", "eMailAddress@dani.ro", "0755001122", "parola.dani");
        User user3 = new User("Luminita Daraban", "lumidaraban", "eMailAddress@luminita.ro", "0743201345", "parola.luminita");


        userService.register(user1);
        userService.register(user2);
        userService.register(user3);


        List<User> usersList = new ArrayList<>();
        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);

        model.addAttribute("myUsersList", usersList);
        return "index";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user, BindingResult errors, Model model) {
        // logic to process input data
        userService.register(user);
        return "saveUser";
    }
}
