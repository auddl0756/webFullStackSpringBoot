package com.example.demo.controller;

import com.example.demo.dto.ProductItemDTO;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainpageController {
    @Autowired
    private ProductService productService;

    private static final int ALL = 0;

    @GetMapping("/api/products/{categoryId}/{start}")
    public List<ProductItemDTO> getProducts(@PathVariable int categoryId, @PathVariable int start) {
        if (categoryId == ALL) {
            return productService.getAllProducts(start);
        } else {
            return productService.getProductsByCategory(categoryId,start);
        }
    }
}
