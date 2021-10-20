package com.example.demo.reserve.dto.displayInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class DisplayInfoDTO {
    private Integer displayInfoId;
    private Integer productId;
    private String description;
    private String openingHours;
    private String placeName;
    private String saveFileName;

    private String reservationDate; //server made random reservation date.

    public void makeRandomReservationDate(DateTimeFormatter formatter) {
        LocalDate date = LocalDate.now().plusDays((int) (Math.random() * 5));
        this.reservationDate = formatter.format(date);
    }
}
