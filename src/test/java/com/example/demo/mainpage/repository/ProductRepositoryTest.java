package com.example.demo.mainpage.repository;

import com.example.demo.mainpage.dto.ProductItemDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void notNullTest(){
        assertThat(productRepository).isNotNull();
    }

    @Test
    public void getProductsByCategoryWithPaging() {
        Pageable pageable = PageRequest.of(1, 4, Sort.Direction.DESC,"displayInfoId");
        List<ProductItemDTO> result
                = productRepository.getProductItemsUsingCategoryWithPaging(1, pageable);

        result.forEach(System.out::println);
    }
}