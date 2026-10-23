package com.example.salonSearcher.repository;

import com.example.salonSearcher.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    boolean existsByEmail(String email);
}
