package com.example.demo.reserve.repository;

import com.example.demo.entity.DisplayInfo;
import com.example.demo.reserve.dto.displayInfo.DisplayInfoDTO;
import com.example.demo.reserve.dto.displayInfo.PriceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Integer> {
    @Query(
            "SELECT new com.example.demo.reserve.dto.displayInfo.DisplayInfoDTO (" +
                    "di.id," +
                    "di.product.id," +
                    "di.product.description," +
                    "di.openingHours," +
                    "di.placeName," +
                    "pi.fileInfo.saveFileName," +
                    " '') " +   /*reservationDate는 서버에서 랜덤으로 만들 것인데, 프로젝션을 위해 일단 빈문자열을 select하도록 함. */
                    "FROM DisplayInfo di " +
                    "JOIN ProductImage pi ON di.product = pi.product " +
                    "WHERE di.id = :displayInfoId " +
                    "AND pi.type = 'ma' "
    )
    DisplayInfoDTO findDisplayInfoDtoById(@Param("displayInfoId") int displayInfoId);

    @Query(
            "SELECT new com.example.demo.reserve.dto.displayInfo.PriceDTO (" +
                    "pp.id," +
                    "pp.priceTypeName, " +
                    "pp.price * (100.0 - pp.discountRate) / 100," +
                    "pp.discountRate" +
                    ") " +
                    "FROM DisplayInfo di " +
                    "JOIN ProductPrice pp ON di.product = pp.product " +
                    "WHERE di.id = :displayInfoId "
    )
    List<PriceDTO> findPriceInfoById(@Param("displayInfoId") int displayInfoId);
}
