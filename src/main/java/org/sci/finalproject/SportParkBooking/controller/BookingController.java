package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.model.*;
import org.sci.finalproject.SportParkBooking.repo.BookingRepo;
import org.sci.finalproject.SportParkBooking.repo.PlayGroundRepo;
import org.sci.finalproject.SportParkBooking.repo.UserRepo;
import org.sci.finalproject.SportParkBooking.service.BookingService;
import org.sci.finalproject.SportParkBooking.service.PlayGroundService;
import org.sci.finalproject.SportParkBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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
    private PlayGroundService playGroundService;
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
            LocalDate now = LocalDate.now();
            model.addAttribute("now", now);
            return "selectBookingDate";
        }
        if (userID!=null) {
            LocalDate now = LocalDate.now();
            model.addAttribute("now", now);
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

        java.util.Date dateNow = new java.util.Date();

        List hourAvailableList = new ArrayList<String>();
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "09:00" + "_" + playGroundID) == null
            && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"09:00" + ":00").after(dateNow)) {
            hourAvailableList.add("09:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "10:00" + "_" + playGroundID) == null
                && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"10:00" + ":00").after(dateNow)) {
            hourAvailableList.add("10:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "11:00" + "_" + playGroundID) == null
                && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"11:00" + ":00").after(dateNow)) {
            hourAvailableList.add("11:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "12:00" + "_" + playGroundID) == null
                && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"12:00" + ":00").after(dateNow)) {
            hourAvailableList.add("12:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "13:00" + "_" + playGroundID) == null
                && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"13:00" + ":00").after(dateNow)) {
            hourAvailableList.add("13:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "14:00" + "_" + playGroundID) == null
                && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"14:00" + ":00").after(dateNow)) {
            hourAvailableList.add("14:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "15:00" + "_" + playGroundID) == null
                && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"15:00" + ":00").after(dateNow)) {
            hourAvailableList.add("15:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "16:00" + "_" + playGroundID) == null
                && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"16:00" + ":00").after(dateNow)) {
            hourAvailableList.add("16:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "17:00" + "_" + playGroundID) == null
                && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"17:00" + ":00").after(dateNow)) {
            hourAvailableList.add("17:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "18:00" + "_" + playGroundID) == null
                && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"18:00" + ":00").after(dateNow)) {
            hourAvailableList.add("18:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "19:00" + "_" + playGroundID) == null
                && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"19:00" + ":00").after(dateNow)) {
            hourAvailableList.add("19:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "20:00" + "_" + playGroundID) == null
                && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"20:00" + ":00").after(dateNow)) {
            hourAvailableList.add("20:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "21:00" + "_" + playGroundID) == null
                && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"21:00" + ":00").after(dateNow)) {
            hourAvailableList.add("21:00");
        }
        if (bookingRepo.findByBookingSignature(booking.getBookingDate() + "_" + "22:00" + "_" + playGroundID) == null
                && java.sql.Timestamp.valueOf(booking.getBookingDate() + " " +"22:00" + ":00").after(dateNow)) {
            hourAvailableList.add("22:00");
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
        String bookingDateString = booking.getBookingDate().toString();
        String bookingHourString = booking.getBookingHour();
        //java.sql.Timestamp.valueOf("2021.05.23 09:00:00");
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
            if (selectedBookingHour.equals("23:00")) {
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

    @RequestMapping("/myBookings")
    public String myBookings(@ModelAttribute("user") User user,
                             Model model) {
        model.addAttribute("user", user);
        return "myBookings";
    }


    @RequestMapping("/userBookings")
    public String userBookings(@ModelAttribute("booking") Booking booking, @ModelAttribute("user") User user,
                               @RequestParam(value="userID", required=false) Long userID,
                               Model model) {

        List<Booking> bookingList = new ArrayList<>();
        List<PlayGround> playGroundList = new ArrayList<>();
        Iterable<Booking> iterableBooking = bookingService.findAll();
        Iterable<PlayGround> iterablePlayGround = playGroundService.findAll();

        if (userID==null && userService.login(user)==true) {
            userID = userRepo.findByUserEmail(user.getUserEmail()).getUserID();
        }
        user.setUserEmail(userRepo.findByUserID(userID).getUserEmail());
        user.setUserPassword(userRepo.findByUserID(userID).getUserPassword());

        Iterator<Booking> iteratorBooking = iterableBooking.iterator();
        while (iteratorBooking.hasNext()) {
            Booking elementBooking = iteratorBooking.next();
            if (userService.login(user)==true && userID==elementBooking.getUserID()) {
                bookingList.add(elementBooking);
            }

            Timestamp dateBooking = java.sql.Timestamp.valueOf(elementBooking.getBookingDate().toString() + " " + elementBooking.getBookingHour() + ":00");
            java.util.Date dateNow = new java.util.Date();
            Boolean isBookingExpired = dateBooking.before(dateNow);
            if (isBookingExpired){
                elementBooking.setBookingStatus(BookingStatusEnum.FINALIZED.name());
                bookingService.setBookingStatus(bookingRepo.findByBookingID(elementBooking.getBookingID()),BookingStatusEnum.FINALIZED.name());
            }
        }

        Iterator<PlayGround> iteratorPlayGround = iterablePlayGround.iterator();
        while (iteratorPlayGround.hasNext()) {
            PlayGround elementPlayGround = iteratorPlayGround.next();
            if (userService.login(user)==true) {
                playGroundList.add(elementPlayGround);
            }
        }


        java.util.Date dateNow = new java.util.Date();

        model.addAttribute("userID", userID);
        model.addAttribute("myBookingList", bookingList);
        model.addAttribute("myPlayGroundList", playGroundList);
        model.addAttribute("dateNow", dateNow);

        return "userBookings";
    }


    @RequestMapping("/cancelBooking")
    public ModelAndView cancelBooking(@ModelAttribute("booking") Booking booking,
                                      @ModelAttribute("user") User user,
                                      @RequestParam(value="userID", required=false) Long userID,
                                      Model model) {

        bookingService.setBookingStatus(bookingRepo.findByBookingID(booking.getBookingID()), BookingStatusEnum.CANCELED.name());

        ModelAndView mv = new ModelAndView("redirect:/userBookings");
        mv.addObject("userID", userID);
        return mv;
    }
}