package com.example.demo.detail.repository;

import com.example.demo.detail.dto.DetailTitleItemDTO;
import com.example.demo.entity.ProductImage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<ProductImage, Integer> {
    @Query(
            "SELECT di.id AS displayInfoId," +
                    "di.product.content AS content," +
                    "di.product.description AS description," +
                    "di.product.id AS productId," +
                    "pi.fileInfo.saveFileName AS productImageUrl " +
                    "FROM ProductImage  pi " +
                    "JOIN DisplayInfo  di ON pi.product = di.product " +
                    "WHERE (" +
                    "   pi.type='th' " +
                    "   OR pi.type = 'et' " +
                    ")" +
                    "AND di.id = :displayInfoId"
    )
    List<DetailTitleItemDTO> findDetailItemsByDisplayInfoId(@Param("displayInfoId") int displayInfoId, Pageable pageable);
}
