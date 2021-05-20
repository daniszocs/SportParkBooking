package org.sci.finalproject.SportParkBooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GalleryController {
    @GetMapping({"/gallery"})
    public String aboutUs(Model model) {

        return "gallery";
    }
}
