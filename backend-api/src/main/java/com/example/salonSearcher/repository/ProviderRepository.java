package com.example.salonSearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.salonSearcher.entity.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
}
