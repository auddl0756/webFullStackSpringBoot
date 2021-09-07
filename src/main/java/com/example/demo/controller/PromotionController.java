package com.example.demo.controller;

import com.example.demo.entity.Promotion;
import com.example.demo.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PromotionController {
    @Autowired
    private PromotionRepository promotionRepository;

    @GetMapping("/api/promotions")
    public String getPromotionInfo(){
//        List<Promotion> list = promotionRepository.findAll();
//        System.out.println(list.get(0));

        return "1";
    }
}
