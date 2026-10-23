package com.example.salonSearcher.dto;

import jakarta.validation.constraints.Size;

/** All fields optional: only non-null fields are applied. */
public record AppointmentUpdateRequest(
        @Size(max = 30) String status,
        @Size(max = 2000) String notes,
        @Size(max = 50) String level,
        @Size(max = 255) String location) {
}
