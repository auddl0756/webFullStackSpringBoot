package com.example.demo.reserve.repository;

import com.example.demo.reserve.dto.displayInfo.DisplayInfoDTO;
import com.example.demo.reserve.dto.displayInfo.PriceDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DisplayInfoRepositoryTest {
    @Autowired
    private DisplayInfoRepository displayInfoRepository;

    @Test
    public void 전시정보_가져오기_Test() {
        DisplayInfoDTO result = displayInfoRepository.findDisplayInfoDtoById(1);
        System.out.println(result);
    }

    @Test
    public void 전시가격정보_가져오기_Test() {
        List<PriceDTO> result = displayInfoRepository.findPriceInfoById(1);

        for (PriceDTO dto : result) {
            System.out.println(dto);
        }
    }
}