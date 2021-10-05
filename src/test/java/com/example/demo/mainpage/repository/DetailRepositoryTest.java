package com.example.demo.mainpage.repository;

import com.example.demo.detail.dto.DetailItemDTO;
import com.example.demo.detail.repository.DetailRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DetailRepositoryTest {
    @Autowired
    private DetailRepository detailRepository;

    @Test
    public void findDetailItemsTest(){
        List<DetailItemDTO> result = detailRepository.findDetailItemsByDisplayInfoId(3);

        result.forEach(item->{
            System.out.println(item.getDisplayInfoId()+" "+item.getProductImageUrl());
        });
    }
}