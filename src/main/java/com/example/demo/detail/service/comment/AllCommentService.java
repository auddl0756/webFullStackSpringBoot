package com.example.demo.detail.service.comment;

import com.example.demo.detail.dto.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllCommentService extends AbstractCommentService {
    @Override
    public List<CommentDTO> getCommentsOnly(int displayInfoId) {
        return commentRepository.getAllCommentsByDisplayInfoId(displayInfoId);
    }
}
