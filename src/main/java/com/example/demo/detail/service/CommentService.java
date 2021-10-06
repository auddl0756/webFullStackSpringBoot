package com.example.demo.detail.service;

import com.example.demo.detail.dto.CommentDTO;
import com.example.demo.detail.dto.CommentImageDTO;
import com.example.demo.detail.dto.ConcreteCommentDTO;
import com.example.demo.detail.repository.CommentImageRepository;
import com.example.demo.detail.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentImageRepository imageRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<ConcreteCommentDTO> getCommentsByDisplayInfoId(int displayInfoId) {
        List<ConcreteCommentDTO> result = new ArrayList<>();

        List<CommentDTO> comments = commentRepository.getCommentsByDisplayInfoId(displayInfoId);

        //ConcreteDTO로 옮겨담는 작업.
        //이 작업은 불가피한가..
        for (CommentDTO com : comments) {
            ConcreteCommentDTO dto = ConcreteCommentDTO.builder()
                    .commentId(com.getCommentId())
                    .comment(com.getComment())
                    .commentCreateDate(com.getCommentCreateDate())
                    .commentModifyDate(com.getCommentModifyDate())
                    .productId(com.getProductId())
                    .reservationInfoId(com.getReservationInfoId())
                    .reservationDate(com.getReservationDate())
                    .reservationEmail(com.getReservationEmail())
                    .reservationName(com.getReservationName())
                    .reservationTelephone(com.getReservationTelephone())
                    .score(com.getScore())
                    .build();

            List<CommentImageDTO> images = imageRepository.getCommentImagesByCommentId(com.getCommentId());
            dto.setImages(images);

            result.add(dto);
        }

        return result;
    }
}
