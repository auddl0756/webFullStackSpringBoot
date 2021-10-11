package com.example.demo.detail.service.comment;

import com.example.demo.detail.dto.ConcreteCommentDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PagedCommentServiceTest {
    @Autowired
    private PagedCommentService pagedCommentService;

    @Test
    public void getInitialCommentsTest() {  //comment와 comment별 이미지들 가져오기 테스트
        List<ConcreteCommentDTO> result = pagedCommentService.getComments(1).getComments();

        assertThat(result).size().isLessThanOrEqualTo(3);

        for (ConcreteCommentDTO dto : result) {
            System.out.print(dto.getCommentId() + " " + dto.getComment() + " " + dto.getScore() + " ");
            if (dto.getImage() != null) {
                System.out.print(dto.getImage().getSaveFileName());
            }
            System.out.println();
        }
    }
}