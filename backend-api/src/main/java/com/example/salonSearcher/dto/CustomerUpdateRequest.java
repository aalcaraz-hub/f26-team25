package com.example.salonSearcher.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
    
public record CustomerUpdateRequest (
    @NotBlank @Size(max = 100) String name,
    @Email @Size(max = 254) String email,
    @Size(max = 20) String phoneNumber
) { 
}
