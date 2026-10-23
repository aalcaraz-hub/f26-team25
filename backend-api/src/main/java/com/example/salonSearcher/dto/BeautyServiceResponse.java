package com.example.salonSearcher.dto;

import java.math.BigDecimal;

import com.example.salonSearcher.entity.BeautyService;

public record BeautyServiceResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer durationMinutes,
        Long providerId,
        String providerName) {

    public static BeautyServiceResponse from(BeautyService s) {
        return new BeautyServiceResponse(s.getId(), s.getName(), s.getDescription(), s.getPrice(),
                s.getDurationMinutes(), s.getProvider().getId(), s.getProvider().getName());
    }
}
