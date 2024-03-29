package com.example.springmvcdemo.contracts;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthCheckDTO {
    private String status;
    private LocalDateTime currentTime;
}
