package com.example.demo.reserve.controller;

import com.example.demo.reserve.dto.reservation.BookingFormRequest;
import com.example.demo.reserve.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/api/reservations")
    public HttpStatus makeReservation(@RequestBody BookingFormRequest reservationParam){
        System.out.println(reservationParam);

        bookingService.makeReservation(reservationParam);

        return HttpStatus.OK;
    }
}
