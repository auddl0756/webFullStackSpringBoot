package com.example.demo.repository;

import com.example.demo.entity.DisplayInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Integer> {
//    # product ,promotion , product_image, file_info
//    select pi.id,pd.id,fi.save_file_name
//    from product_image pi
//    join product pd on pi.product_id = pd.id
//    join file_info fi on fi.id = pi.file_id
//    join promotion pr on pr.product_id = pd.id
//    join category ct on ct.id = pd.category_id
//    where pi.type='th';

    @Query("SELECT pi.id,pi.product.id,pi.fileInfo.saveFileName " +
            "FROM ProductImage pi " +
            "JOIN Promotion pr ON pr.product = pi.product " +
            "WHERE pi.type='th' ")
    List<Object[]> getPromotionImages();

//    @Query("SELECT pi.id,pi.product.id,pi.fileInfo.saveFileName " +
//            "FROM ProductImage pi " +
//            "JOIN Product pd ON pi.product = pd " +
//            "JOIN Promotion pr ON pr.product = pd " +
//            "JOIN Category ct ON pd.category = ct " +
//            "JOIN FileInfo fi ON fi = pi.fileInfo " +
//            "WHERE pi.type='th' ")
//    List<Object[]> getPromotionImages();
}
