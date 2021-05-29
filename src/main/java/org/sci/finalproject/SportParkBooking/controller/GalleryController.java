package org.sci.finalproject.SportParkBooking.controller;

import org.sci.finalproject.SportParkBooking.service.PlayGroundService;
import org.sci.finalproject.SportParkBooking.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GalleryController {
    @Autowired
    private PlayGroundService playGroundService;
    @Autowired
    private SportService sportService;

    @GetMapping({"/gallery"})
    public String gallery(Model model) {
        return "gallery";
    }

    @GetMapping({"/galleryFotbal"})
    public String galleryFotbal(Model model) {
        return "galleryFotbal";
    }

    @GetMapping({"/galleryTenis"})
    public String galleryTenis(Model model) {
        return "galleryTenis";
    }
}
