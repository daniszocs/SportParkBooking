package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.Booking;
import org.sci.finalproject.SportParkBooking.model.BookingStatusEnum;
import org.sci.finalproject.SportParkBooking.model.User;
import org.sci.finalproject.SportParkBooking.repo.PlayGroundRepo;
import org.sci.finalproject.SportParkBooking.repo.UserRepo;
import org.sci.finalproject.SportParkBooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PlayGroundRepo playGroundRepo;
    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/booking")
    public String myBookingPage(@ModelAttribute("user") User user,
            @RequestParam(value="userID", required=false) String userID,
            Model model) {
        model.addAttribute("userID", userID);
        if (userID == null) {
            return "loginOrRegister";
        }
        else {
            return "selectSport";
        }
    }

    @RequestMapping("/selectBooking")
    public String mySelectBookingPage(@ModelAttribute("user") User user,
            @RequestParam(value="userID", required=false) String userID,
            @RequestParam(value="playGroundID", required=false) String playGroundID,
            Model model) {
        Booking booking = new Booking();
        model.addAttribute("booking", booking);
        model.addAttribute("userID", userID);
        model.addAttribute("playGroundID", playGroundID);
        booking.setPlayGroundID(Long.parseLong(playGroundID));
        booking.setUserID(Long.parseLong(userID));
        return "selectBooking";
    }

        @RequestMapping({"/confirmBooking"})
    public String confirmBooking(@ModelAttribute("booking") Booking booking,
                                 @RequestParam(value="userID", required=false) String userID,
                                 @RequestParam(value="playGroundID", required=false) String playGroundID,
                                 BindingResult errors, Model model) {
//      java.sql.Timestamp.valueOf("2008-04-06 09:01:10");
        model.addAttribute("userID", userID);
        model.addAttribute("playGroundID", playGroundID);
        model.addAttribute("booking", booking);
        booking.setPlayGroundID(Long.parseLong(playGroundID));
        booking.setUserID(Long.parseLong(userID));
        booking.setBookingStatus(BookingStatusEnum.ACTIVE.name());
        booking.setBookingPrice(playGroundRepo.findByPlayGroundID(Long.parseLong(playGroundID)).getPricePerHour()* booking.getBookingDuration());
        boolean confirmBookingResult = bookingService.saveNewBooking(booking);
        String userName = userRepo.findByUserID(Long.parseLong(userID)).getUserName();
        model.addAttribute("userName", userName);
        if (confirmBookingResult) {
            return "confirmBooking";
        } else {
            return "error";
        }
    }

}