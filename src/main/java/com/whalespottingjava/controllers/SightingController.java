package com.whalespottingjava.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SightingController {
    @GetMapping("/add-whale-sighting")
    public String getAddSightingPage() {
        return "add_sighting";
    }
    @GetMapping("/Sightings") //returns both approved and non approved. Not to be used by users other than admin or for testing.
    public String getAllSightings(){return "Sightings";}

    @GetMapping("/PendingSightings") // For Admin
    public String GetPendingSightings()
    {
        //IF LOGGED IN == ADMIN
        return "PendingSightings";
        //ELSE "YOU'RE NOT AN ADMIN GO AWAY"
    }

    @GetMapping("/ApprovedSightings") // For normal user
    public String GetApprovedSightings()
    {
        return  "ApprovedSightings";
    }
}
