package com.example.demo.detail.repository;

import com.example.demo.detail.dto.DetailTitleItemDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DetailRepositoryTest {
    @Autowired
    private DetailRepository detailRepository;

    @Test
    public void findDetailItemsTest() {
        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.ASC, "displayInfoId");
        List<DetailTitleItemDTO> result = detailRepository.findDetailItemsByDisplayInfoId(2, pageable);

        result.forEach(item -> {
            System.out.println(item.getDisplayInfoId() + " " + item.getProductImageUrl());
        });
    }
}