package com.example.salonSearcher.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/** Body for POST /api/services. The provider is linked by id. */
public record BeautyServiceRequest(
        @NotBlank @Size(max = 100) String name,
        @Size(max = 2000) String description,
        @PositiveOrZero BigDecimal price,
        @PositiveOrZero Integer durationMinutes,
        @NotNull Long providerId) {
}
