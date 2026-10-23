package com.example.salonSearcher.dto;

import jakarta.validation.constraints.Size;

/** All fields optional: only non-null fields are applied. */
public record ProviderUpdateRequest(
        @Size(max = 100) String name,
        @Size(max = 20) String phoneNumber,
        @Size(max = 30) String accountStatus,
        @Size(max = 100) String businessName,
        @Size(max = 255) String address,
        @Size(max = 2000) String bio) {
}
