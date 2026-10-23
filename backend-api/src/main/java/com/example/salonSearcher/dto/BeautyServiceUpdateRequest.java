package com.example.salonSearcher.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/** Body for PUT /api/services/{id}. The owning provider cannot be changed. */
public record BeautyServiceUpdateRequest(
        @NotBlank @Size(max = 100) String name,
        @Size(max = 2000) String description,
        @PositiveOrZero BigDecimal price,
        @PositiveOrZero Integer durationMinutes) {
}
