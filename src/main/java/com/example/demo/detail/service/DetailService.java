package com.example.demo.detail.service;

import com.example.demo.detail.dto.DetailItemDTO;
import com.example.demo.detail.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {
    @Autowired
    private DetailRepository detailRepository;

    public List<DetailItemDTO> getTitleData(int displayInfoId){
        return detailRepository.findDetailItemsByDisplayInfoId(displayInfoId);
    }
}
