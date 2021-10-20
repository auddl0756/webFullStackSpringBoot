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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;
    private String description;
    private String event;

    private String createDate;
    private String modifyDate;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
