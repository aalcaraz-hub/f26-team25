package com.example.salonSearcher.service;

import java.util.Locale;

import org.springframework.stereotype.Service;
import com.example.salonSearcher.repository.CustomerRepository;
import com.example.salonSearcher.dto.CustomerRegistrationRequest;
import com.example.salonSearcher.dto.CustomerResponse;
import com.example.salonSearcher.dto.CustomerUpdateRequest;
import com.example.salonSearcher.entity.Customer;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional 
public class CustomerService {
    private final CustomerRepository customerRepository;
    
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse register(CustomerRegistrationRequest request) {
        ensureEmailAvailable(request.email());
        Customer customer = new Customer();
        customer.setName(request.name().trim());
        customer.setEmail(request.email().trim().toLowerCase(Locale.ROOT));
        customer.setPassword(request.password());
        customer.setAccountStatus("ACTIVE");
        customer.setPhoneNumber(request.phoneNumber());
        return CustomerResponse.from(customerRepository.save(customer));
    }

    @Transactional(readOnly = true)
    public CustomerResponse getProfile(Long customerId) {
        return CustomerResponse.from(findCustomer(customerId));
    }

    public CustomerResponse updateProfile(Long customerId, CustomerUpdateRequest request) {
        Customer customer = findCustomer(customerId);
        if (!customer.getEmail().equalsIgnoreCase(request.email())
                && customerRepository.existsByEmailIgnoreCase(request.email())) {
            throw new ConflictException("An account with that email already exists.");
        }
        customer.setName(request.name().trim());
        customer.setEmail(request.email().trim().toLowerCase(Locale.ROOT));
        customer.setPhoneNumber(request.phoneNumber());
        return CustomerResponse.from(customerRepository.save(customer));
    }

    private Customer findCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    private void ensureEmailAvailable(String email) {
        if (customerRepository.existsByEmailIgnoreCase(email)) {
            throw new ConflictException("Email is already in use");
        }
    }
}