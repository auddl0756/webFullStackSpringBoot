package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class ProductPrice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String priceTypeName;
    private int price;
    private double discountRate;
    private String createDate;
    private String modifyDate;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
}
