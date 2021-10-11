package com.example.demo.detail.service;

import com.example.demo.detail.dto.CommentDTO;
import com.example.demo.detail.dto.CommentImageDTO;
import com.example.demo.detail.dto.CommentResponseDTO;
import com.example.demo.detail.dto.ConcreteCommentDTO;
import com.example.demo.detail.repository.CommentImageRepository;
import com.example.demo.detail.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentImageRepository imageRepository;

    @Autowired
    private CommentRepository commentRepository;

    private static final int IMAGE_PAGE_SIZE = 1;

    public CommentResponseDTO getCommentsByDisplayInfoId(int displayInfoId) {
        List<ConcreteCommentDTO> commentsWithImages = new ArrayList<>();

        List<CommentDTO> commentsOnly = commentRepository.getCommentsByDisplayInfoId(displayInfoId);

        double averageScore = 0;

        //ConcreteDTO로 옮겨담는 작업.
        //이 작업은 불가피한가..
        for (CommentDTO com : commentsOnly) {
            averageScore += com.getScore();

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
                    .score(String.format("%.1f", com.getScore()))
                    .build();

            //comment의 이미지 가져오기
            Pageable pageable = PageRequest.of(0, IMAGE_PAGE_SIZE, Sort.Direction.ASC, "reservationUserCommentId");
            Page<CommentImageDTO> commentImage = imageRepository.getCommentImagesByCommentId(com.getCommentId(),pageable);

            if(commentImage.isEmpty()){
                dto.setImage(null);
            }else{
                dto.setImage(commentImage.getContent().get(0));
            }

            commentsWithImages.add(dto);
        }

        if(commentsOnly.isEmpty() == false){
            averageScore /= commentsOnly.size();
        }

        return CommentResponseDTO.builder().
                comments(commentsWithImages).
                averageScore(String.format("%.2f", averageScore)).
                build();
    }
}
