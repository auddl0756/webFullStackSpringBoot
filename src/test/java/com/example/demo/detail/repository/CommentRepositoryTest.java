package com.example.demo.detail.repository;

import com.example.demo.detail.dto.CommentDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void getInitialCommentsByDisplayInfoIdTest() {
        Pageable commentPageable = PageRequest.of(0,
                3,
                Sort.Direction.DESC,
                "id");
        List<CommentDTO> result = commentRepository.getInitialCommentsByDisplayInfoId(1, commentPageable);

        System.out.println(result.size());

        result.forEach(x -> {
            assertThat(x.getCommentId()).isNotNull();

            System.out.println(x.getCommentId() + " " + x.getComment() + " "
                    + x.getCommentCreateDate() + " " + x.getScore());
        });
    }
}
