package com.example.demo.detail.repository;

import com.example.demo.detail.dto.CommentImageDTO;
import com.example.demo.entity.ReservationUserCommentImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentImageRepository extends JpaRepository<ReservationUserCommentImage,Integer> {
    @Query("SELECT fi.contentType AS contentType," +
            "fi.createDate AS createDate," +
            "fi.modifyDate AS modifyDate," +
            "fi.deleteFlag AS deleteFlag," +
            "fi.id AS fileId," +
            "fi.fileName AS fileName," +
            "fi.saveFileName AS saveFileName," +
            "ci.reservationInfo.id AS reservationInfoId," +
            "ci.reservationUserComment.id AS reservationUserCommentId," +
            "ci.id AS imageId " +
            "FROM ReservationUserCommentImage ci " +
            "JOIN FileInfo fi ON ci.fileInfo = fi " +
            "WHERE ci.reservationUserComment.id = :commentId"
    )
    Page<CommentImageDTO> getCommentImagesByCommentId(@Param("commentId") int commentId, Pageable pageable);
}
