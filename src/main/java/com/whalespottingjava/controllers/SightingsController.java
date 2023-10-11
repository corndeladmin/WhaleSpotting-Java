package com.whalespottingjava.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SightingsController {
    @GetMapping("/sightings")
    public String getSightingsPage() {
        return "sightings";
    }
}
