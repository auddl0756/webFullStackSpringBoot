package com.example.demo.detail.service.comment;

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

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.detail.dto.ConcreteCommentDTO.maskEmailAndReturn;

public abstract class AbstractCommentService {
    //template method 패턴 쓰면 괜찮을 것 같았다
    //왜냐하면 댓글을 페이징해서 가져오는 코드와 전체 댓글을 가져오는 메서드가 코드가 많이 비슷해서

    protected static final int FIRST_PAGE_NUMBER = 0;
    protected static final int COMMENT_PAGE_SIZE = 3;
    protected final int IMAGE_PAGE_SIZE = 1;

    @Autowired
    protected CommentImageRepository imageRepository;

    @Autowired
    protected CommentRepository commentRepository;

    public abstract List<CommentDTO> getCommentsOnly(int displayInfoId);

    public final CommentResponseDTO getComments(int displayInfoId) {
        List<CommentDTO> commentsOnly = getCommentsOnly(displayInfoId);
        List<ConcreteCommentDTO> commentsWithImages = new ArrayList<>();
        double averageScore = 0;

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
                    .reservationEmail(maskEmailAndReturn(com.getReservationEmail()))
                    .reservationName(com.getReservationName())
                    .reservationTelephone(com.getReservationTelephone())
                    .score(String.format("%.1f", com.getScore()))
                    .build();

            //comment의 이미지 가져오기
            Pageable pageable = PageRequest.of(FIRST_PAGE_NUMBER,
                    IMAGE_PAGE_SIZE,
                    Sort.Direction.DESC,
                    "reservationUserCommentId");

            Page<CommentImageDTO> commentImage = imageRepository.getCommentImagesByCommentId(com.getCommentId(), pageable);

            if (commentImage.isEmpty()) {
                dto.setImage(null);
            } else {
                dto.setImage(commentImage.getContent().get(0));
            }

            commentsWithImages.add(dto);
        }

        if (commentsOnly.isEmpty() == false) {
            averageScore /= commentsOnly.size();
        }

        return CommentResponseDTO.builder().
                comments(commentsWithImages).
                averageScore(String.format("%.2f", averageScore)).
                build();
    }
}
