package com.example.jwtpractice2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String user() {
        return "User Page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin Page";
    }

}
