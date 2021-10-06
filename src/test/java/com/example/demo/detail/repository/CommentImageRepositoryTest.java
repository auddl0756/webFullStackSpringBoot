package com.example.demo.detail.repository;

import com.example.demo.detail.dto.CommentImageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentImageRepositoryTest {
    @Autowired
    private CommentImageRepository commentImageRepository;

    @Test
    public void getCommentImagesByIdTest(){
        List<CommentImageDTO> result = commentImageRepository.getCommentImagesByCommentId(1);

        result.forEach(x->{
            System.out.println(x.getImageId()+" "+x.getFileName()+" "+x.getCreateDate()+" "+x.isDeleteFlag());
        });
    }
}