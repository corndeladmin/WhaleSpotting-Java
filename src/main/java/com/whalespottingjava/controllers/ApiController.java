package com.whalespottingjava.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {
    @GetMapping("/api")
    public String getApiDocPage() {
        return "api";
    }

    @PostMapping("/api")
    @CrossOrigin(origins = "*")
    public HttpStatus postWhale(
            @PathVariable String whaleName
    ) {
        System.out.println(whaleName);

        return HttpStatus.OK;
    }
}
