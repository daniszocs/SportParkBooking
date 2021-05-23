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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
        model.addAttribute("userID", userID);
        model.addAttribute("playGroundID", playGroundID);
        model.addAttribute("booking", booking);

        //get name to display "Hello name, registration is successful!"
        String userName = userRepo.findByUserID(Long.parseLong(userID)).getUserName();
        model.addAttribute("userName", userName);

//      java.sql.Timestamp.valueOf("2021.05.23 09:00:00");
        Timestamp bookingHourTimeStampStart = java.sql.Timestamp.valueOf(booking.getBookingDate() + " " + booking.getBookingHour() + ":00");
        boolean confirmBookingResult=true;
        for (int i = 1; i <= booking.getBookingDuration(); i++) {
            //each booking is one hour, starting with booking hour.
            Booking oneBooking = new Booking();
            //set bookingDate
            System.out.println(booking.getBookingDate());
            oneBooking.setBookingDate(booking.getBookingDate());
            //set bookingHour
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String oneBookingHour = sdf.format(bookingHourTimeStampStart.getTime() + (1000 * 60 * 60 * (i-1)));
            oneBooking.setBookingHour(oneBookingHour);
            //set bookingDuration
            oneBooking.setBookingDuration(1);
            //set bookingPrice
            oneBooking.setBookingPrice(playGroundRepo.findByPlayGroundID(Long.parseLong(playGroundID)).getPricePerHour());
            //set bookingUserID
            oneBooking.setUserID(Long.parseLong(userID));
            //set bookingPlayGroundID
            oneBooking.setPlayGroundID(Long.parseLong(playGroundID));
            //set bookingStatus
            oneBooking.setBookingStatus(BookingStatusEnum.ACTIVE.name());
            //save booking and confirm
            confirmBookingResult = confirmBookingResult && bookingService.saveNewBooking(oneBooking);
        }

        if (confirmBookingResult) {
            return "confirmBooking";
        } else {
            return "bookingNotAvailable";
        }
    }

}