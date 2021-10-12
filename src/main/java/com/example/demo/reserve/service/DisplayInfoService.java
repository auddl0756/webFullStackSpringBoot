package com.example.demo.reserve.service;

import com.example.demo.reserve.dto.displayInfo.DisplayInfoDTO;
import com.example.demo.reserve.dto.displayInfo.DisplayInfoResponseDTO;
import com.example.demo.reserve.dto.displayInfo.PriceDTO;
import com.example.demo.reserve.repository.DisplayInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DisplayInfoService {
    @Autowired
    private DisplayInfoRepository displayInfoRepository;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DisplayInfoResponseDTO getDisplayInfo(int displayInfoId) {
        DisplayInfoDTO displayInfo = displayInfoRepository.findDisplayInfoDtoById(displayInfoId);
        List<PriceDTO> priceInfos = displayInfoRepository.findPriceInfoById(displayInfoId);

        displayInfo.makeRandomReservationDate(formatter);
        priceInfos.forEach(PriceDTO::makeActualPriceTypeName);

        return DisplayInfoResponseDTO.builder()
                .displayInfo(displayInfo)
                .priceInfos(priceInfos)
                .build();
    }
}
