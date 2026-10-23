package com.example.salonSearcher.repository;

import com.example.salonSearcher.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByCustomerIdOrderByReviewDateDesc(Long customerId);

    List<Review> findAllByProviderIdOrderByReviewDateDesc(Long providerId);

    long countByProviderId(Long providerId);

    @Query("select avg(r.rating) from Review r where r.provider.id = :providerId")
    Double averageRatingForProvider(@Param("providerId") Long providerId);
}
