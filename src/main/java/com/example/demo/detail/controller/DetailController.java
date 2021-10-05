package com.example.demo.detail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailController {
    @GetMapping("/detail")
    public String detailPage(@RequestParam(name="id") int displayInfoId){
        System.out.println(displayInfoId);
        return "detail";
    }
}
