package com.example.salonSearcher.service;

import com.example.salonSearcher.entity.Appointment;
import com.example.salonSearcher.entity.Review;
import com.example.salonSearcher.repository.AppointmentRepository;
import com.example.salonSearcher.repository.CustomerRepository;
import com.example.salonSearcher.repository.ProviderRepository;
import com.example.salonSearcher.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import com.example.salonSearcher.dto.ReviewRequest;
import com.example.salonSearcher.dto.ReviewResponse;

@Service
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final ProviderRepository providerRepository;

    public ReviewService(
        ReviewRepository reviewRepository,
        AppointmentRepository appointmentRepository,
        CustomerRepository customerRepository,
        ProviderRepository providerRepository) {
        this.reviewRepository = reviewRepository;
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
    }

    public ReviewResponse create(Long customerId, Long appointmentId, ReviewRequest request) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException(
                        "Appointment " + appointmentId + " was not found."));
        if (!appointment.getCustomer().getId().equals(customerId)) {
            throw new NotFoundException("Appointment " + appointmentId + " was not found for customer "
                    + customerId + ".");
        }
        if (!"COMPLETED".equalsIgnoreCase(appointment.getStatus())) {
            throw new ConflictException("A review can only be submitted for a completed appointment.");
        }
        if (appointment.getReview() != null) {
            throw new ConflictException("A review already exists for this appointment.");
        }

        Review review = new Review();
        review.setAppointment(appointment);
        review.setCustomer(appointment.getCustomer());
        review.setProvider(appointment.getProvider());
        review.setRating(request.rating());
        review.setReviewDate(LocalDateTime.now());
        appointment.setReview(review);
        return ReviewResponse.from(reviewRepository.save(review));
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> listForCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new NotFoundException("Customer " + customerId + " was not found.");
        }
        return reviewRepository.findAllByCustomerIdOrderByCreatedAtDesc(customerId)
                .stream().map(ReviewResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> listForProvider(Long providerId) {
        if (!providerRepository.existsById(providerId)) {
            throw new NotFoundException("Provider " + providerId + " was not found.");
        }
        return reviewRepository.findAllByProviderIdOrderByCreatedAtDesc(providerId)
                .stream().map(ReviewResponse::from).toList();
    }

    public ReviewResponse reply(Long providerId, Long reviewId, ReviewReplyRequest request) {
        if (!providerRepository.existsById(providerId)) {
            throw new NotFoundException("Provider " + providerId + " was not found.");
        }
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review " + reviewId + " was not found."));
        if (!review.getProvider().getId().equals(providerId)) {
            throw new NotFoundException("Review " + reviewId + " was not found for provider "
                    + providerId + ".");
        }
        review.setReplyText(request.replyText().trim());
        return ReviewResponse.from(reviewRepository.save(review));
    }
}