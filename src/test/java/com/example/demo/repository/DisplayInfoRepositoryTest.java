package com.example.demo.repository;


import com.example.demo.entity.DisplayInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DisplayInfoRepositoryTest {
    @Autowired
    private DisplayInfoRepository displayInfoRepository;

    @Test
    public void 프로모션영역_이미지구하기() {
        List<Object[]> result = displayInfoRepository.getPromotionImages();
        result.forEach(info -> {
            int productImageId = (Integer) info[0];
            int productId = (Integer) info[1];
            String saveFileName = (String) info[2];

            System.out.println(productImageId + " " + productId + " " + saveFileName);
        });
    }
}