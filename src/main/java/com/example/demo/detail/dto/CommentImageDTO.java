package com.example.demo.detail.dto;

public interface CommentImageDTO {
    String getContentType();
    String getCreateDate();
    String getModifyDate();
    boolean isDeleteFlag();
    int getFileId();
    String getFileName();
    String getSaveFileName();
    int getImageId();
    int getReservationInfoId();
    int getReservationUserCommentId();
}
