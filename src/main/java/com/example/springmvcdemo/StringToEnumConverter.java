package com.example.springmvcdemo;

import com.example.springmvcdemo.contracts.HealthCheckType;
import org.springframework.core.convert.converter.Converter;

/**
 * Simple converter so params passed in request URLs can be lowercase and still match to enum values
 */
public class StringToEnumConverter implements Converter<String, HealthCheckType> {
    @Override
    public HealthCheckType convert(String queryParam) {
        return HealthCheckType.valueOf(queryParam.toUpperCase());
    }
}
