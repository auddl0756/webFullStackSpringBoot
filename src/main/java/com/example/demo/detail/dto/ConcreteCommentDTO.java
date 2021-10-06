package com.example.demo.detail.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ConcreteCommentDTO {
    private String comment;
    private int commentId;
    private String commentCreateDate;
    private String commentModifyDate;
    private int productId;
    private String reservationDate;
    private String reservationEmail;
    private int reservationInfoId;
    private String reservationName;
    private String reservationTelephone;
    private String score;

    private List<CommentImageDTO> images;
}
