package com.example.demo.mainpage.controller;

import com.example.demo.mainpage.dto.PromotionImageDTO;
import com.example.demo.mainpage.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @GetMapping("/api/promotions")
    public List<PromotionImageDTO> getPromotionInfo(){
        return promotionService.getPromotionImages();
    }
}
