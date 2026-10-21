package com.example.salonSearcher.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record TimeslotRequest(
        @NotNull LocalDateTime startTime,
        @NotNull LocalDateTime endTime,
        @NotNull Boolean isAvailable) {
}
