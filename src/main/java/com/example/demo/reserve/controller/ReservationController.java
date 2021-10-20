package com.example.demo.reserve.controller;

import com.example.demo.reserve.dto.reservation.ReservationSaveDTO;
import com.example.demo.reserve.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/api/reservations")
    public HttpStatus makeReservation(@RequestBody ReservationSaveDTO reservationParam){
        System.out.println(reservationParam);

        reservationService.makeReservation(reservationParam);

        return HttpStatus.OK;
    }
}
