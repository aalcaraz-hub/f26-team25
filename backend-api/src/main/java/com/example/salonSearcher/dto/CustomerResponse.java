package com.example.salonSearcher.dto;

import com.example.salonSearcher.entity.Customer;

public record CustomerResponse(
    Long id,
    String name,
    String email,
    String phoneNumber,
    String accountStatus
) {
    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
            customer.getId(),
            customer.getName(),
            customer.getEmail(),
            customer.getPhoneNumber(),
            customer.getAccountStatus()
        );
    }
}