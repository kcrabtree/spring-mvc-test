package com.example.springmvcdemo.service;

import com.example.springmvcdemo.contracts.HealthCheckDTO;
import com.example.springmvcdemo.contracts.HealthCheckType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HealthService {
    public HealthCheckDTO check(HealthCheckType type) {
        HealthCheckDTO result = HealthCheckDTO.builder().status(HttpStatus.OK.getReasonPhrase()).build();

        if(type == HealthCheckType.FULL) {
            result.setCurrentTime(LocalDateTime.now());
        }

        return result;
    }
}
