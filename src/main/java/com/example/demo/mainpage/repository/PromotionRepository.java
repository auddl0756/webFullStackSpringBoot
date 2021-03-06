package com.example.demo.mainpage.repository;

import com.example.demo.mainpage.dto.PromotionImageDTO;
import com.example.demo.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion,Integer> {
    @Query("SELECT pi.id AS productImageId," +
            "pi.product.id AS productId," +
            "pi.fileInfo.saveFileName AS saveFileName," +
            "pi.product.description AS description " +
            "FROM ProductImage pi " +
            "JOIN Promotion pr ON pr.product = pi.product " +
            "WHERE pi.type='th' ")
    List<PromotionImageDTO> getPromotionImages();
}
