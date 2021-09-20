package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class ReservationInfoPrice {
    @Id @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="reservation_info_id")
    private ReservationInfo reservationInfo;

    @ManyToOne
    @JoinColumn(name="product_price_id")
    private ProductPrice productPrice;
}
