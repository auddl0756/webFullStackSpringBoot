package com.example.demo.reserve.dto.reservation;

import com.example.demo.reserve.dto.booking.FormPrice;
import com.example.demo.reserve.dto.displayInfo.PriceDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Setter
@Getter
public class BookingFormRequest {
    private String name;
    private String tel;
    private String email;
    private int displayInfoId;
    private int productId;
    private List<FormPrice> prices;
}
