package com.example.demo.detail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailController {
    @GetMapping("/detail")
    public String detailPage(@RequestParam(name = "id") int displayInfoId, Model model) {
        model.addAttribute("displayInfoId", displayInfoId);
        return "detail";
    }

    @GetMapping("/review")
    public String reviewPage(@RequestParam(name = "id") int displayInfoId, Model model) {
        model.addAttribute("displayInfoId", displayInfoId);
        return "review";
    }

    @GetMapping("/reserve")
    public String reservePage(@RequestParam(name="id") int displayInfoId, Model model){
        model.addAttribute("displayInfoId", displayInfoId);
        return "reserve";
    }
}
