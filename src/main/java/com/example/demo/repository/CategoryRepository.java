package com.example.demo.repository;

import com.example.demo.dto.CategoryTabDTO;
import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT di.product.category.id AS id," +
            "di.product.category.name AS name," +
            "COUNT(di.product.category) AS count " +
            "FROM DisplayInfo di " +
            "GROUP BY di.product.category.id")
    List<CategoryTabDTO> getCategories();
}
