package com.example.demo.reserve.service;

import com.example.demo.reserve.dto.displayInfo.DisplayInfoDTO;
import com.example.demo.reserve.dto.displayInfo.DisplayInfoResponseDTO;
import com.example.demo.reserve.dto.displayInfo.PriceDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DisplayInfoServiceTest {
    @Autowired
    private DisplayInfoService displayInfoService;

    @Test
    public void 통합전시정보_가져오기_Test() {
        DisplayInfoResponseDTO result = displayInfoService.getDisplayInfo(1);

        DisplayInfoDTO displayInfo = result.getDisplayInfo();
        List<PriceDTO> priceInfos = result.getPriceInfos();

        System.out.println(displayInfo);
        priceInfos.forEach(System.out::println);

        assertThat(displayInfo.getDisplayInfoId()).isEqualTo(1);
    }
}