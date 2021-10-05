package com.example.demo.mainpage.service;

import com.example.demo.mainpage.dto.ProductItemDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void test(){
        List<ProductItemDTO> result = productService.getProductsByCategory(1,1);
        result.forEach(x->{
            System.out.println(x.getDisplayInfoId()+" "+x.getPlaceName());
        });
    }
}