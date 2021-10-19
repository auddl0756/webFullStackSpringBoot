package com.example.demo.reserve.controller;

import com.example.demo.reserve.dto.reservation.FormRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @PostMapping("/api/reservations")
    public HttpStatus makeReservation(@RequestBody FormRequest reservationParam){

        return HttpStatus.OK;
    }
}
