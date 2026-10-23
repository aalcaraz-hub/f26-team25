package com.example.salonSearcher.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/** Body for POST /api/customers/{customerId}/appointments */
public record BookRequest(
        @NotNull Long beautyServiceId,
        @NotNull Long timeslotId,
        @Size(max = 2000) String notes,
        @Size(max = 50) String level,
        @Size(max = 255) String location) {
}
