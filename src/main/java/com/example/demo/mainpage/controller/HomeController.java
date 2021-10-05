package com.example.demo.mainpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String mainpage(){
        return "mainpage";
    }
}
