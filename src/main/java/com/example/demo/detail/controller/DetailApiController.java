package com.example.demo.detail.controller;

import com.example.demo.detail.dto.CommentResponseDTO;
import com.example.demo.detail.dto.DetailTitleItemDTO;
import com.example.demo.detail.service.comment.AbstractCommentService;
import com.example.demo.detail.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DetailApiController {
    @Autowired
    private DetailService detailService;

    @Qualifier("allCommentService")
    @Autowired
    private AbstractCommentService allCommentService;

    @Qualifier("pagedCommentService")
    @Autowired
    private AbstractCommentService pagedCommentService;

    //detail 페이지 타이틀 영역 데이터 요청
    @GetMapping("/api/detailTitleData/{displayInfoId}")
    public List<DetailTitleItemDTO> getTitleData(@PathVariable int displayInfoId) {
        return detailService.getTitleData(displayInfoId);
    }

    //detail 페이지 초기 댓글(최대 3개) 요청
    @GetMapping("/api/comments/initial/{displayInfoId}")
    public CommentResponseDTO getInitialComments(@PathVariable int displayInfoId) {
        return pagedCommentService.getComments(displayInfoId);
    }

    //review 페이지. 모든 댓글 요청
    @GetMapping("/api/comments/{displayInfoId}")
    public CommentResponseDTO getAllCommentsData(@PathVariable int displayInfoId){
        return allCommentService.getComments(displayInfoId);
    }

}
