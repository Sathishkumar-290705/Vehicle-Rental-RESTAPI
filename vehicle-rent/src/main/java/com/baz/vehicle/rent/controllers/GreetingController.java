package com.baz.vehicle.rent.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GreetingController {
    @GetMapping("/")
    public String Greeting() {
        String welcomeMessage = "Welcome to Vehicle Rest API";
        return welcomeMessage;
    }
}
