package com.whalespottingjava.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ApiController {
    @GetMapping("/api")
    public String getApiDocPage() {
        return "api";
    }

    @PostMapping("/api")
    public String postWhale() {
//        System.out.println(whaleName);

        return "OK";
    }
}
