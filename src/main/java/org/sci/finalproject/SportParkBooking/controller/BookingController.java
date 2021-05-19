package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.Booking;
import org.sci.finalproject.SportParkBooking.service.PlayGroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {
    @Autowired
    private PlayGroundService playGroundService;


    @RequestMapping("/selectBooking")
    public String mySelectBookingPage(Model model) {
        Booking emptyBooking = new Booking();
        model.addAttribute("booking", emptyBooking);
        return "selectBooking";
    }

//
//
//    @RequestMapping("/register")
//    public String myRegisterPage(Model model) {
//        User emptyUser = new User();
//        model.addAttribute("user", emptyUser);
//        return "register";
//    }
//
//    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
//    public String registerUser(@ModelAttribute("user") User user, BindingResult errors, Model model) {
//        boolean registerResult = userService.register(user);
//        if (registerResult) {
//            return confirmRegister(model, user.getUserName());
//        } else {
//            return "error";
//        }
//    }
//
//    @GetMapping({"/confirmRegister"})
//    public String confirmRegister(Model model, @RequestParam(value="name", required=false) String name) {
//        model.addAttribute("name", name);
//        return "confirmRegistration";
//    }
}
