package com.example.demo.reserve.dto.displayInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
public class PriceDTO {
    private int productPriceId;
    private String priceTypeName;
    private double discountedPrice;
    private double discountRate;

    public void makeActualPriceTypeName() {
        ActualPriceTypeName typeName = ActualPriceTypeName.valueOf(priceTypeName);
        this.priceTypeName = typeName.getActualTypeName();
    }
}
