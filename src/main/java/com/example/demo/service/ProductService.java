package com.example.demo.service;

import com.example.demo.dto.ProductItemDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    private static final int PAGING_SIZE = 4;

    public List<ProductItemDTO> getProductsByCategory(int categoryId, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, PAGING_SIZE, Sort.Direction.DESC, "displayInfoId");
        return productRepository.getProductItemsUsingCategoryWithPaging(categoryId, pageable);
    }

    public List<ProductItemDTO> getAllProducts(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, PAGING_SIZE, Sort.Direction.DESC, "displayInfoId");
        return productRepository.getAllProductItemWithPaging(pageable);
    }
}
