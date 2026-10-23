package com.example.salonSearcher.dto;

import java.time.LocalDateTime;
import com.example.salonSearcher.entity.Review;

public record ReviewResponse(
        Long id,
        Integer rating,
        String comments,
        String replyText,
        LocalDateTime reviewDate,
        Long customerId,
        String customerName,
        Long providerId,
        String providerName,
        Long appointmentId) {

    public static ReviewResponse from(Review review) {
        return new ReviewResponse(review.getId(), review.getRating(), review.getComments(),
                review.getReplyText(), review.getReviewDate(), review.getCustomer().getId(),
                review.getCustomer().getName(), review.getProvider().getId(),
                review.getProvider().getName(),
                review.getAppointment().getId());
    }
}