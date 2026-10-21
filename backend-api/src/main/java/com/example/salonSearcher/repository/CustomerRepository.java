package com.example.salonSearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.salonSearcher.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmailIgnoreCase(String email);
}
