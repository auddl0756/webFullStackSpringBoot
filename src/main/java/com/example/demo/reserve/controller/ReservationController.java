package com.example.demo.reserve.controller;

import com.example.demo.reserve.dto.reservation.ReservationSaveDTO;
import com.example.demo.reserve.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/api/reservations")
    public HttpStatus makeReservation(@Valid @RequestBody ReservationSaveDTO reservationParam, Errors errors){
        System.out.println(reservationParam);

        if(errors.hasErrors()){
            List<ObjectError> errorList = errors.getAllErrors();
            for(ObjectError error : errorList){
                System.out.println(error.getDefaultMessage());
            }

            return HttpStatus.BAD_REQUEST;
        }else{
            reservationService.makeReservation(reservationParam);
            return HttpStatus.CREATED;  // 201
        }
    }
}
