package com.example.demo.reserve.repository;

import com.example.demo.entity.ReservationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationInfo,Integer> {
}
