package com.example.demo.reserve.dto.reservation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Min;

@ToString
@Setter
@Getter
public class FormPrice {
    @Min(value = 1,message = "invalid productPriceId")
    private int productPriceId;

    @Min(value = 1,message = "ticket count is zero.")
    private int count;
}
