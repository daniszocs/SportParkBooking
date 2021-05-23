package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.Booking;
import org.sci.finalproject.SportParkBooking.model.BookingStatusEnum;
import org.sci.finalproject.SportParkBooking.model.User;
import org.sci.finalproject.SportParkBooking.repo.BookingRepo;
import org.sci.finalproject.SportParkBooking.repo.PlayGroundRepo;
import org.sci.finalproject.SportParkBooking.repo.UserRepo;
import org.sci.finalproject.SportParkBooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PlayGroundRepo playGroundRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookingRepo bookingRepo;

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

    @RequestMapping("/selectBookingDate")
    public String mySelectBookingDatePage(@ModelAttribute("booking") Booking booking,
            @RequestParam(value="userID", required=false) String userID,
            @RequestParam(value="playGroundID", required=false) String playGroundID,
            Model model) {
        model.addAttribute("booking", booking);
        model.addAttribute("userID", userID);
        model.addAttribute("playGroundID", playGroundID);
        //model.addAttribute(booking.getBookingDate());
        booking.setPlayGroundID(Long.parseLong(playGroundID));
        booking.setUserID(Long.parseLong(userID));

        return "selectBookingDate";
    }

    //RequestParam hourList
    @RequestMapping("/selectBooking")
    public String mySelectBookingPage(@ModelAttribute("booking") Booking booking,
                                      @RequestParam(value="userID", required=false) String userID,
                                      @RequestParam(value="playGroundID", required=false) String playGroundID,
                                      Model model) {
//        Booking booking = new Booking();
        model.addAttribute("booking", booking);
        model.addAttribute("userID", userID);
        model.addAttribute("playGroundID", playGroundID);
        booking.setPlayGroundID(Long.parseLong(playGroundID));
        booking.setUserID(Long.parseLong(userID));

        List hourAvailableList = new ArrayList<String>();
        //bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "09:00" + "_" + playGroundID);
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "09:00" + "_" + playGroundID) == null) {
            hourAvailableList.add("09:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "10:00" + "_" + playGroundID) == null) {
            hourAvailableList.add("10:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "11:00" + "_" + playGroundID) == null) {
            hourAvailableList.add("11:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "12:00" + "_" + playGroundID) == null) {
            hourAvailableList.add("12:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "13:00" + "_" + playGroundID) == null) {
            hourAvailableList.add("13:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "14:00" + "_" + playGroundID) == null) {
            hourAvailableList.add("14:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "15:00" + "_" + playGroundID) == null) {
            hourAvailableList.add("15:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "16:00" + "_" + playGroundID) == null) {
            hourAvailableList.add("16:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "17:00" + "_" + playGroundID) == null) {
            hourAvailableList.add("17:00");
        }

        model.addAttribute("myHourAvailableList",hourAvailableList);
        return "selectBooking";
    }

        @RequestMapping({"/confirmBooking"})
    public String confirmBooking(@ModelAttribute("booking") Booking booking,
                                 @RequestParam(value="userID", required=false) String userID,
                                 @RequestParam(value="playGroundID", required=false) String playGroundID,
                                 BindingResult errors, Model model) {
        //        Booking booking = new Booking();
        model.addAttribute("userID", userID);
        model.addAttribute("playGroundID", playGroundID);
        model.addAttribute("booking", booking);

        //get name to display "Hello name, registration is successful!"
        String userName = userRepo.findByUserID(Long.parseLong(userID)).getUserName();
        model.addAttribute("userName", userName);

//        //check if hour list contains hour that is booked
//        boolean isBooked = false;
//        List hoursBooked = new ArrayList<String>();
//        for (int i = 1; i <= hourAvailableList.size(); i++) {
//            String bookingSignature = booking.getBookingDate() + hourAvailableList.get(i).toString();
//            Booking foundBooking = bookingRepo.findByBookingSignature(bookingSignature);
//            if (foundBooking != null) {
//                isBooked = true;
//                hoursBooked.add(hourAvailableList.get(i));
//            }
//        }
//
//        model.addAttribute("MyHoursBooked",hoursBooked);

//      java.sql.Timestamp.valueOf("2021.05.23 09:00:00");
        Timestamp bookingHourTimeStampStart = java.sql.Timestamp.valueOf(booking.getBookingDate() + " " + booking.getBookingHour() + ":00");
        boolean confirmBookingResult=true;
        for (int i = 1; i <= booking.getBookingDuration(); i++) {
            //each booking is one hour, starting with booking hour.
            Booking oneBooking = new Booking();
            //set bookingDate
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