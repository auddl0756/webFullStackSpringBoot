package com.example.demo.detail.controller;

import com.example.demo.detail.dto.DetailItemDTO;
import com.example.demo.detail.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DetailApiController {
    @Autowired
    private DetailService detailService;

    @GetMapping("/api/detailData/{displayInfoId}")
    public List<DetailItemDTO> getTitleData(@PathVariable int displayInfoId){
        return detailService.getTitleData(displayInfoId);
    }
}
