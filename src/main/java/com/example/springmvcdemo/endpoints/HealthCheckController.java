package com.example.springmvcdemo.endpoints;

import com.example.springmvcdemo.contracts.HealthCheckDTO;
import com.example.springmvcdemo.contracts.HealthCheckType;
import com.example.springmvcdemo.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {

    private final HealthService healthService;

    @GetMapping("healthcheck")
    public ResponseEntity<HealthCheckDTO> healthCheck(@RequestParam HealthCheckType type) {
        return new ResponseEntity<>(healthService.check(type), HttpStatus.OK);
    }
}
