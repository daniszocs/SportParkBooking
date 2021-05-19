package org.sci.finalproject.SportParkBooking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class aboutUsController {

    @Autowired
    private aboutUsController aboutUsController;

    @GetMapping({"/aboutUs"})
    public String aboutUs(Model model) {
        return "aboutUs";
    }

}
