package com.example.demo.mainpage.controller;

import com.example.demo.mainpage.dto.CategoryTabDTO;
import com.example.demo.mainpage.dto.ProductItemDTO;
import com.example.demo.mainpage.service.CategoryService;
import com.example.demo.mainpage.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainpageController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    private static final int ALL = 0;

    @GetMapping("/api/products/{categoryId}/{pageNum}")
    public List<ProductItemDTO> getProducts(@PathVariable int categoryId, @PathVariable int pageNum) {
        if (categoryId == ALL) {
            return productService.getAllProducts(pageNum);
        } else {
            return productService.getProductsByCategory(categoryId,pageNum);
        }
    }

    @GetMapping("/api/categories")
    public List<CategoryTabDTO> getCategories(){
        return categoryService.getCategories();
    }
}
