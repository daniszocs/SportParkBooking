package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.Booking;
import org.sci.finalproject.SportParkBooking.service.BookingService;
import org.sci.finalproject.SportParkBooking.service.PlayGroundService;
import org.sci.finalproject.SportParkBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;

@Controller
public class BookingController {
    @Autowired
    private PlayGroundService playGroundService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;

    @RequestMapping("/selectBooking")
    public String mySelectBookingPage(Model model) {
        Booking emptyBooking = new Booking();
        model.addAttribute("booking", emptyBooking);
        return "selectBooking";
    }

        @RequestMapping({"/confirmBooking"})
    public String confirmBooking(@ModelAttribute("booking") Booking booking, BindingResult errors, Model model) {
        boolean confirmBookingResult = bookingService.saveNewBooking(booking);
        if (confirmBookingResult) {
            return "confirmBooking";
        } else {
            return "error";
        }
    }



//        @RequestMapping({"/confirmBooking"})
//    public String confirmBooking(@ModelAttribute("booking") Booking booking, @ModelAttribute("playGround") PlayGround playGround, @ModelAttribute("user") User user, BindingResult errors, Model model) {
//        long userId = userService.returnUserID(user.getUserName());
//        long playGroundId = playGroundService.returnPlayGroundID(playGround.getPlayGroundName());
//        booking.setUserID(userId);
//        booking.setPlayGroundID(playGroundId);
//        boolean confirmBookingResult = bookingService.saveNewBooking(booking);
//        if (confirmBookingResult) {
//            return "confirmBooking";
//        } else {
//            return "error";
//        }
//    }


}