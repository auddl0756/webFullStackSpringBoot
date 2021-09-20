package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class ReservationUserComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double score;
    private String comment;
    private String createDate;
    private String modifyDate;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
}
