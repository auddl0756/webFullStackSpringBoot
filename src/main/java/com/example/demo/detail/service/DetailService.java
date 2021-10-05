package com.example.demo.detail.service;

import com.example.demo.detail.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailService {
    @Autowired
    private DetailRepository detailRepository;


}
