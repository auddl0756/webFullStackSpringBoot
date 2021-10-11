package com.example.demo.detail.service;

import com.example.demo.detail.dto.DetailTitleItemDTO;
import com.example.demo.detail.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {
    @Autowired
    private DetailRepository detailRepository;
    private static final int PAGE_SIZE = 2;

    public List<DetailTitleItemDTO> getTitleData(int displayInfoId) {
        Pageable pageable = PageRequest.of(0, PAGE_SIZE, Sort.Direction.ASC, "displayInfoId");
        return detailRepository.findDetailItemsByDisplayInfoId(displayInfoId, pageable);
    }
}
