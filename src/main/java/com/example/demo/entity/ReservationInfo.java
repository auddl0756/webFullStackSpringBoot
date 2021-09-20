package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class ReservationInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reservationName;
    private String reservationTel;
    private String reservationEmail;
    private boolean cancelFlag;
    private String reservationDate;
    private String createDate;
    private String modifyDate;


    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="display_info_id")
    private DisplayInfo displayInfo;
}
