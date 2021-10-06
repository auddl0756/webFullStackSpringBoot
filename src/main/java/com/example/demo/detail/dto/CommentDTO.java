package com.example.demo.detail.dto;

import java.util.List;

public interface CommentDTO {
    String getComment();
    int getCommentId();
    String getCommentCreateDate();
    String getCommentModifyDate();
    int getProductId();
    String getReservationDate();
    String getReservationEmail();
    int getReservationInfoId();
    String getReservationName();
    String getReservationTelephone();
    double getScore();

    double getAverageScore();
}
