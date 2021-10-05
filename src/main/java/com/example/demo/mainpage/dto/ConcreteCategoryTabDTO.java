package com.example.demo.mainpage.dto;

import lombok.Getter;

@Getter
public class ConcreteCategoryTabDTO implements CategoryTabDTO{
    private int id;
    private String name;
    private int count;

    public ConcreteCategoryTabDTO(int id,String name,int count){
        this.id = id;
        this.name = name;
        this.count = count;
    }
}
