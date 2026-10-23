package com.example.salonSearcher.dto;

import java.util.Map;

public record ProviderStatisticsResponse(
        Long providerId,
        long totalAppointments,
        long uniqueCustomers,
        Map<String, Long> appointmentsByStatus,
        long totalReviews,
        Double averageRating) {
}
