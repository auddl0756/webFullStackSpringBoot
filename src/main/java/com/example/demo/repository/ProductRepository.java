package com.example.demo.repository;

import com.example.demo.dto.ProductItemDTO;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT  di.id AS displayInfoId," +
            "di.placeName as placeName," +
            "di.product.id AS productId," +
            "di.product.content AS content," +
            "di.product.description AS description," +
            "pi.fileInfo.saveFileName AS productImageUrl " +
            "FROM DisplayInfo di " +
            "JOIN ProductImage  pi ON di.product = pi.product " +
            "WHERE di.product.category.id = :categoryId")
    List<ProductItemDTO> getProductItems(@Param("categoryId") int categoryId);
}