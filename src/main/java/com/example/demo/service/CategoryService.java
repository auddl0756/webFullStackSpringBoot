package com.example.demo.service;

import com.example.demo.dto.CategoryTabDTO;
import com.example.demo.dto.ConcreteCategoryTabDTO;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryTabDTO> getCategories(){
        List<CategoryTabDTO> result = categoryRepository.getCategories();

        int totalCount = result.stream().map(CategoryTabDTO::getCount).reduce(0, Integer::sum);

        CategoryTabDTO totalDTO = new ConcreteCategoryTabDTO(0,"전체",totalCount);
        result.add(0,totalDTO);

        return result;
    }
}
