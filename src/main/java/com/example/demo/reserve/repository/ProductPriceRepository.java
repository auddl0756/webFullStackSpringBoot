package com.example.demo.reserve.repository;

import com.example.demo.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPrice,Integer> {
}
