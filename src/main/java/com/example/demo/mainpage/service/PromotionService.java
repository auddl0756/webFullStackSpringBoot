package com.example.demo.mainpage.service;

import com.example.demo.mainpage.dto.PromotionImageDTO;
import com.example.demo.mainpage.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    public List<PromotionImageDTO> getPromotionImages(){
        return promotionRepository.getPromotionImages();
    }
}
