package com.example.demo.detail.service.comment;

import com.example.demo.detail.dto.CommentDTO;
import com.example.demo.detail.service.comment.AbstractCommentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagedCommentService extends AbstractCommentService {
    @Override
    public List<CommentDTO> getCommentsOnly(int displayInfoId) {
        Pageable commentPageable = PageRequest.of(FIRST_PAGE_NUMBER,
                COMMENT_PAGE_SIZE,
                Sort.Direction.DESC,
                "id");

        return commentRepository.getInitialCommentsByDisplayInfoId(displayInfoId, commentPageable);
    }
}
