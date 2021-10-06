package com.example.demo.detail.dto;

import java.util.List;

public interface CommentDTO {
    String getComment();
    Integer getCommentId(); //int -> Integer (댓글이 없을 수도 있으니..?)
    String getCommentCreateDate();
    String getCommentModifyDate();
    Integer getProductId();
    String getReservationDate();
    String getReservationEmail();
    Integer getReservationInfoId();
    String getReservationName();
    String getReservationTelephone();
    Double getScore();

    Double getAverageScore();
}
