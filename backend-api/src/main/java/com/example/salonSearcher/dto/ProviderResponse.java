package com.example.salonSearcher.dto;

import com.example.salonSearcher.entity.Provider;

public record ProviderResponse(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String accountStatus,
        String businessName,
        String address,
        String bio) {

    public static ProviderResponse from(Provider p) {
        return new ProviderResponse(p.getId(), p.getName(), p.getEmail(), p.getPhoneNumber(),
                p.getAccountStatus(), p.getBusinessName(), p.getAddress(), p.getBio());
    }
}
