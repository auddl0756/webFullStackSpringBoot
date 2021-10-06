package com.example.demo.detail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class CommentResponseDTO {
    List<ConcreteCommentDTO> comments;
    String averageScore;
}
