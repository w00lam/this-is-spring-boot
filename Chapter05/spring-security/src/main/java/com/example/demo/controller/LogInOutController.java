package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LogInOutController {
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/logout")
    public String getLogout() {
        return "logout";
    }
}
