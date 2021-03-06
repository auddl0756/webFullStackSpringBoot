package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class DisplayInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String placeName;
    private String openingHours;
    private String placeLot;
    private String placeStreet;
    private String tel;
    private String homepage;
    private String email;

    private String createDate;
    private String modifyDate;

    @ManyToOne
    @JoinColumn(name= "product_id")
    private Product product;
}
