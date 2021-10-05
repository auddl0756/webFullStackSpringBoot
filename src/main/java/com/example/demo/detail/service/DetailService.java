package com.example.demo.detail.service;

import com.example.demo.detail.dto.DetailItemDTO;
import com.example.demo.detail.repository.DetailRepository;
import com.example.demo.mainpage.dto.ProductItemDTO;
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
    private static final int PAGING_SIZE = 2;

    public List<DetailItemDTO> getTitleData(int displayInfoId) {
        Pageable pageable = PageRequest.of(0, PAGING_SIZE, Sort.Direction.ASC, "displayInfoId");
        return detailRepository.findDetailItemsByDisplayInfoId(displayInfoId, pageable);
    }
}
