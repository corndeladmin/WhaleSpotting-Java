package com.whalespottingjava.controllers;

import com.whalespottingjava.models.MemberDetails;
import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.services.SightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SightingController {
  private final SightingService sightingService;

  @Autowired
  public SightingController(SightingService sightingService) {
    this.sightingService = sightingService;
  }

  // renders the add-sighting form
  @GetMapping("/add-whale-sighting")
  public String getAddSightingPage(Model model) {
    model.addAttribute("sighting", new Sighting());
    return "add_sighting";
  }

  @GetMapping("/sightings/test")
  public String getAllSighting(Model model) {
    model.addAttribute("sightings", sightingService.getAllSightings());
    return "sightings_map";
  }

  //
  //    @GetMapping("/sightings/approved")
  //    public String getAllApprovedSightings(Model model) {
  //        model.addAttribute("approvedSightings", this.sightingService.getAllApprovedSightings());
  //        return "sightings_approved";
  //    }

  // submits the add-sighting form data
  @PostMapping("/add-whale-sighting")
  public String submitSighting(@ModelAttribute Sighting sighting, Model model) {
    model.addAttribute("sighting", sighting);
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
    sighting.setMemberId(memberDetails.getMember().getId());
    sightingService.addSighting(sighting);
    return "add_sighting_confirmation";
  }

  @GetMapping("/sightings")
  public String getAllSightings(Model model) {
    model.addAttribute("sightings", sightingService.getAllSightings());
    return "sighting_test";
  }

  @GetMapping("/sightings/approved")
  @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
  public void getAllApprovedSightings() {}

  @GetMapping("/sightings/pending")
  @ResponseStatus(HttpStatus.OK)
  public String getPendingSightings(Model model) {
    model.addAttribute("sightings", sightingService.getAllPendingSightings());
    
    return "sightings_map";
  }
}
