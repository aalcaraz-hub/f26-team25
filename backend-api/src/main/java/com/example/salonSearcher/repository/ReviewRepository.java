package com.example.salonSearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.salonSearcher.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByCustomerIdOrderByCreatedAtDesc(Long customerId);
    List<Review> findAllByProviderIdOrderByCreatedAtDesc(Long providerId);
}
