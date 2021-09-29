package com.example.demo.service;

import com.example.demo.dto.ProductItemDTO;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductItemDTO> getProductsByCategory(int categoryId, int start) {
        return productRepository.getProductItemsByCategory(categoryId, start);
    }

    public List<ProductItemDTO> getAllProducts(int start) {
        return productRepository.getAllProductItems(start);
    }
}
