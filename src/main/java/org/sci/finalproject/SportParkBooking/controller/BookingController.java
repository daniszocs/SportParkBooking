package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.Booking;
import org.sci.finalproject.SportParkBooking.model.BookingStatusEnum;
import org.sci.finalproject.SportParkBooking.model.User;
import org.sci.finalproject.SportParkBooking.repo.BookingRepo;
import org.sci.finalproject.SportParkBooking.repo.PlayGroundRepo;
import org.sci.finalproject.SportParkBooking.repo.UserRepo;
import org.sci.finalproject.SportParkBooking.service.BookingService;
import org.sci.finalproject.SportParkBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;
    @Autowired
    private PlayGroundRepo playGroundRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookingRepo bookingRepo;

    @RequestMapping("/selectBookingDate")
    public String mySelectBookingDatePage(@ModelAttribute("booking") Booking booking,@ModelAttribute("user") User user,
            @RequestParam(value="userID", required=false) Long userID,
            @RequestParam(value="playGroundName", required=false) String playGroundName,
            Model model) {

        //if userID is not given as attribute, get userID from userEmail.
        // ex: from login or register you have info about userEmail
        //from bookingNotAvailable you have info about userID

        if (userID==null && userService.login(user)==true) {
            userID = userRepo.findByUserEmail(user.getUserEmail()).getUserID();
        }
        user.setUserID(userID);
        booking.setUserID(userID);

        model.addAttribute("booking", booking);
        model.addAttribute("userID", user.getUserID());
        model.addAttribute("playGroundName", playGroundName);

        if (userID==null && userService.login(user)==true) {
            return "selectBookingDate";
        }
        if (userID!=null) {
            return "selectBookingDate";
        }
        return "error";
    }

    @RequestMapping("/selectBookingHours")
    public String mySelectBookingHoursPage(@ModelAttribute("booking") Booking booking,
                                      @RequestParam(value="userID", required=false) String userID,
                                      @RequestParam(value="playGroundName", required=false) String playGroundName,
                                      Model model) {
        model.addAttribute("booking", booking);
        model.addAttribute("userID", userID);
        model.addAttribute("playGroundName", playGroundName);
        Long playGroundID = (playGroundRepo.findByPlayGroundName(playGroundName)).getPlayGroundID();
        booking.setPlayGroundID(playGroundID);

        List hourAvailableList = new ArrayList<String>();
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
        if (hourAvailableList.size()==0){
            hourAvailableList.add("No available hours");
        }
        model.addAttribute("myHourAvailableList",hourAvailableList);
        model.addAttribute("myBookingDate",booking.getBookingDate().toString());
        return "selectBookingHours";
    }

    @RequestMapping({"/infoBooking"})
    public String infoBooking(@ModelAttribute("booking") Booking booking,
                                 @RequestParam(value="userID", required=false) Long userID,
                                 @RequestParam(value="playGroundName", required=false) String playGroundName,
                                 BindingResult errors, Model model) {
        model.addAttribute("userID", userID);
        model.addAttribute("playGroundName", playGroundName);
        int bookingPrice = playGroundRepo.findByPlayGroundName(playGroundName).getPricePerHour() * booking.getBookingDuration();
        model.addAttribute("bookingPrice", bookingPrice);
        String userName = userRepo.findByUserID(userID).getUserName();
        model.addAttribute("userName", userName);

        Long playGroundID = playGroundRepo.findByPlayGroundName(playGroundName).getPlayGroundID();

        //create booked Hours list
        List hoursBookedList = new ArrayList<String>();
        boolean isBooked = false;
        //java.sql.Timestamp.valueOf("2021.05.23 09:00:00");
        String bookingDateString = booking.getBookingDate().toString();
        String bookingHourString = booking.getBookingHour();
        Timestamp bookingHourTimeStampStart = java.sql.Timestamp.valueOf(booking.getBookingDate().toString() + " " + booking.getBookingHour() + ":00");
        for (int i = 1; i <= booking.getBookingDuration(); i++) {

            //get selected booking Hours
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String selectedBookingHour = sdf.format(bookingHourTimeStampStart.getTime() + (1000 * 60 * 60 * (i-1)));

            //check if selected booking Hour is found in db
            //if found, add to booked Hours list
            String bookingSignature = booking.getBookingDate() + "_" + selectedBookingHour + "_" + playGroundID;
            Booking foundBooking = bookingRepo.findByBookingSignature(bookingSignature);
            if (foundBooking != null) {
                isBooked = true;
                hoursBookedList.add(selectedBookingHour);
            }
        }

        model.addAttribute("myHoursBooked",hoursBookedList);

        if (!isBooked) {
            return "infoBooking";
        } else {
            return "bookingNotAvailable";
        }
    }

    @RequestMapping({"/confirmBooking"})
    public String confirmBooking(@ModelAttribute("booking") Booking booking,
                                 @RequestParam(value="userID", required=false) Long userID,
                                 @RequestParam(value="playGroundName", required=false) String playGroundName,
                                 @RequestParam(value="bookingDate", required=false) Date bookingDate,
                                 BindingResult errors, Model model) {
        model.addAttribute("userID", userID);
        model.addAttribute("playGroundName", playGroundName);
        booking.setBookingDate(bookingDate);
        Long playGroundID = playGroundRepo.findByPlayGroundName(playGroundName).getPlayGroundID();
        String userName = userRepo.findByUserID(userID).getUserName();
        model.addAttribute("userName", userName);

        // create booking in db if hours are available.
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
                oneBooking.setBookingPrice(playGroundRepo.findByPlayGroundID(playGroundID).getPricePerHour());
                //set bookingUserID
                oneBooking.setUserID(userID);
                //set bookingPlayGroundID
                oneBooking.setPlayGroundID(playGroundID);
                //set bookingStatus
                oneBooking.setBookingStatus(BookingStatusEnum.ACTIVE.name());
                //save booking and confirm
                confirmBookingResult = confirmBookingResult && bookingService.saveNewBooking(oneBooking);
            }
        return "confirmBooking";
    }
}