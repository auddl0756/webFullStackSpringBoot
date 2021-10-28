package com.example.demo.reserve.service;

import com.example.demo.entity.*;
import com.example.demo.reserve.dto.reservation.FormPrice;
import com.example.demo.reserve.dto.reservation.ReservationSaveDTO;
import com.example.demo.reserve.repository.ProductPriceRepository;
import com.example.demo.reserve.repository.ReservationInfoPriceRepository;
import com.example.demo.reserve.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {
    /* 묵시적 생성자 주입 방식 with ReqiredArgsConstructor. Autowired가 너무 많아져서.*/
    private final ReservationRepository reservationRepository;
    private final ProductPriceRepository productPriceRepository;
    private final ReservationInfoPriceRepository infoPriceRepository;

    public void makeReservation(ReservationSaveDTO reservationParam) {
        DisplayInfo displayInfo = DisplayInfo.builder()
                .id(reservationParam.getDisplayInfoId())
                .build();

        Product product = Product.builder()
                .id(reservationParam.getProductId())
                .build();

        ReservationInfo reservationInfo = ReservationInfo.builder()
                .reservationName(reservationParam.getName())
                .reservationEmail(reservationParam.getEmail())
                .reservationDate(reservationParam.getReservationDate())
                .reservationTel(reservationParam.getTel())
                .displayInfo(displayInfo)
                .product(product)
                .build();

        reservationRepository.save(reservationInfo);    //1. 연관관계의 pk를 가지고 있는 ReservationInfo 테이블에 먼저 저장

        List<FormPrice> prices = reservationParam.getPrices();

        for (FormPrice price : prices) {
            // 2. ProductPrice는 새로 저장할 필요없이 그냥 가져다 쓰는 용도의 테이블.
            ProductPrice productPrice = ProductPrice.builder().id(price.getProductPriceId()).build();
            //ProductPrice productPrice = productPriceRepository.getOne(price.getProductPriceId());

            //3. 연관관계의 fk를 가지고 있는 쪽인 reservation_info_price를 pk쪽들을 저장한 다음에 저장.
            ReservationInfoPrice infoPrice = ReservationInfoPrice.builder()
                    .reservationInfo(reservationInfo)
                    .productPrice(productPrice)
                    .count(price.getCount())
                    .build();

            infoPriceRepository.save(infoPrice);
        }
    }
}
