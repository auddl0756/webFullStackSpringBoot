package com.example.demo.repository;

import com.example.demo.dto.CategoryTabDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void getAllCategoriesTest(){
        List<CategoryTabDTO> result = categoryRepository.getCategories();

        result.forEach(dto->{
            System.out.println(dto.getId()+" "+dto.getName()+" "+dto.getCount());

            //실제 DB를 보면 상품마다 몇십개씩 존재하므로
            //각 카테고리마다 상품 적어도 1개씩은 있어야 함.
            assertThat(dto.getCount()).isGreaterThanOrEqualTo(1);
        });
    }
}