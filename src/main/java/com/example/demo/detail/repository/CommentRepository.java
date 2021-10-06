package com.example.demo.detail.repository;

import com.example.demo.detail.dto.CommentDTO;
import com.example.demo.detail.dto.ConcreteCommentDTO;
import com.example.demo.entity.ReservationUserComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<ReservationUserComment, Integer> {
    @Query(
            "SELECT AVG(com.score) AS averageScore," +      //"ROUND function" 사용 불가...
                    "com.id AS commentId," +
                    "com.comment AS comment," +
                    "com.createDate AS commentCreateDate," +
                    "com.modifyDate AS commentModifyDate," +
                    "com.product.id AS productId," +
                    "com.reservationInfo.reservationDate AS reservationDate," +
                    "com.reservationInfo.reservationEmail AS reservationEmail," +
                    "com.reservationInfo.id AS reservationInfoId," +
                    "com.reservationInfo.reservationName AS reservationName," +
                    "com.reservationInfo.reservationTel AS reservationTelephone," +
                    "com.score AS score " +
                    "FROM ReservationUserComment com " +
                    "WHERE com.reservationInfo.displayInfo.id = :displayInfoId"
    )
    public List<CommentDTO> getCommentsByDisplayInfoId(@Param("displayInfoId") int displayInfoId);

}
