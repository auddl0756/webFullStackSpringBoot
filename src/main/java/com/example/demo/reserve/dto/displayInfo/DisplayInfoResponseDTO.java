package com.example.demo.reserve.dto.displayInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class DisplayInfoResponseDTO {
    private DisplayInfoDTO displayInfo;
    private List<PriceDTO> priceInfos;
}
