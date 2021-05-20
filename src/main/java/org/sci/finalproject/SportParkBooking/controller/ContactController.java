package org.sci.finalproject.SportParkBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {
    @GetMapping({"/contact"})
    public String aboutUs(Model model) {
        return "contact";
    }

}
