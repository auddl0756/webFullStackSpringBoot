package com.example.demo.detail.repository;

import com.example.demo.detail.dto.CommentDTO;
import com.example.demo.detail.dto.ConcreteCommentDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void getCommentByDisplayInfoIdTest(){
        List<CommentDTO> result = commentRepository.getCommentsByDisplayInfoId(1);

        result.forEach(x->{
            System.out.println(x.getCommentId()+" "+x.getComment()+" "+x.getCommentCreateDate());
        });
    }
}
