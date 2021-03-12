package com.vgtstptlk.jwtexampleproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/security")
public class SecurityController {

    @GetMapping("/admin")
    public String getAdmin(){
        return "Hi,Admin!";
    }

    @GetMapping("/user")
    public String getUser(){
        return "Hi, User!";
    }
}
