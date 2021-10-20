package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@ToString(exclude = {"reservation_info","product_price"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class ReservationInfoPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int count;

    @ManyToOne
    @JoinColumn(name="reservation_info_id")
    private ReservationInfo reservationInfo;

    @ManyToOne
    @JoinColumn(name="product_price_id")
    private ProductPrice productPrice;
}
