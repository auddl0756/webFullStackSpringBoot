package com.example.demo.reserve.dto.reservation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import java.util.List;

@ToString
@Setter
@Getter
public class ReservationSaveDTO {
    @NotNull
    @Size(min=1,max=34,message = "invalid name length")
    @Pattern(regexp = "(^[A-Za-z]+$)|([ㄱ-ㅎ|ㅏ-ㅣ|가-힣])")
    private String name;

    @NotNull
    @Pattern(regexp = "\\d{3}[-]\\d{4}[-]\\d{4}",message = "invalid telephone number")
    private String tel;

    @NotNull
    @Email(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+[.][a-zA-Z0-9]+$",message = "invalid email")
    private String email;

    @NotNull
    private String reservationDate;

    @NotNull
    private int displayInfoId;

    @NotNull
    private int productId;

    @Size(min=1,message = "no ticket is in reservation request.")
    private List<FormPrice> prices;
}
