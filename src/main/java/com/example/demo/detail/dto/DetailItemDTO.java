package com.example.demo.detail.dto;

public interface DetailItemDTO {
    Integer getDisplayInfoId();
    String getContent();
    String getDescription();
    Integer getProductId();
    String getProductImageUrl();
    //events are empty in DB.. so exclude.
}
