package com.example.salonSearcher.repository;

import com.example.salonSearcher.entity.BeautyService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeautyServiceRepository extends JpaRepository<BeautyService, Long> {
    List<BeautyService> findAllByProviderId(Long providerId);
}
