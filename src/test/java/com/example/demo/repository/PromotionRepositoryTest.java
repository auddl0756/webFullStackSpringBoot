package com.example.demo.repository;

import com.example.demo.dto.PromotionImageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PromotionRepositoryTest {
    @Autowired
    private PromotionRepository promotionRepository;

    @Test
    public void 프로모션영역_이미지구하기() {
        List<PromotionImageDTO> result = promotionRepository.getPromotionImages();
        result.forEach(info -> {
            int productImageId = info.getProductImageId();
            int productId = info.getProductId();
            String saveFileName = info.getSaveFileName();

            System.out.println(productImageId + " " + productId + " " + saveFileName);
        });
    }
}