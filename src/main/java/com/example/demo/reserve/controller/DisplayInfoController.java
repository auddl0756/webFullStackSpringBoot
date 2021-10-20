package com.example.demo.reserve.controller;

import com.example.demo.reserve.dto.displayInfo.DisplayInfoResponseDTO;
import com.example.demo.reserve.service.DisplayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DisplayInfoController {
    @Autowired
    private DisplayInfoService displayInfoService;

    @GetMapping("/reserve/displayInfo/{displayInfoId}")
    public ResponseEntity<DisplayInfoResponseDTO> getDisplayInfo(@PathVariable int displayInfoId) {
        if (displayInfoId < 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        DisplayInfoResponseDTO result = displayInfoService.getDisplayInfo(displayInfoId);
        Integer responseDisplayInfoId = result.getDisplayInfo().getDisplayInfoId();

        if (responseDisplayInfoId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
