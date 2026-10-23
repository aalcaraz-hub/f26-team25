package com.example.salonSearcher.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProviderRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Email @Size(max = 254) String email,
        @Size(max = 20) String phoneNumber,
        @NotBlank @Size(max = 255) String password,
        @Size(max = 100) String businessName,
        @Size(max = 255) String address,
        @Size(max = 2000) String bio) {
}
