package com.example.salonSearcher.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/** Body for POST /api/customers/{customerId}/appointments/{appointmentId}/review */
public record ReviewRequest(
        @NotNull @Min(1) @Max(5) Integer rating,
        @Size(max = 2000) String comments) {
}
