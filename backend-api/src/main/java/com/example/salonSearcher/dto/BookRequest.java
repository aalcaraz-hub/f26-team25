package com.example.salonSearcher.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookRequest(
    @NotNull Long providerId,
    @NotNull Long serviceId,
    @NotNull Long timeSlotId,
    @Size(max = 500) String notes) {
}