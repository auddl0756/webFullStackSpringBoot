package com.example.demo.repository;

import com.example.demo.dto.PromotionImageDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion,Integer> {
    @Query("SELECT pi.id AS productImageId," +
            "pi.product.id AS productId," +
            "pi.fileInfo.saveFileName AS saveFileName " +
            "FROM ProductImage pi " +
            "JOIN Promotion pr ON pr.product = pi.product " +
            "WHERE pi.type='th' ")
    List<PromotionImageDTO> getPromotionImages();
}
