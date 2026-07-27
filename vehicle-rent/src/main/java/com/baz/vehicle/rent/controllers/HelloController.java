package com.baz.vehicle.rent.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        String msg = "Hello I am running in 5000";
        return msg;
    }
}
