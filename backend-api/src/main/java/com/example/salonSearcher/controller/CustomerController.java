package com.example.salonSearcher.controller;

import com.example.salonSearcher.dto.*;
import com.example.salonSearcher.entity.Appointment;
import com.example.salonSearcher.service.CustomerService;
import com.example.salonSearcher.service.ReviewService;
import com.example.salonSearcher.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final AppointmentService appointmentService;
    private final ReviewService reviewService;

    public CustomerController(
        CustomerService customerService,
        AppointmentService appointmentService,
        ReviewService reviewService) {
        this.customerService = customerService;
        this.appointmentService = appointmentService;
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> register(
        @Valid @RequestBody CustomerRegistrationRequest request) {
        CustomerResponse customer = customerService.register(request);
        return ResponseEntity.created(URI.create("/api/customers/" + customer.id())).body(customer);
    }

    @GetMapping("/{customerId}")
    public CustomerResponse getProfile(@PathVariable Long customerId) {
        return customerService.getProfile(customerId);
    }

    @PutMapping("/{customerId}")
    public CustomerResponse updateProfile(
        @PathVariable Long customerId,
        @Valid @RequestBody CustomerUpdateRequest request) {
        return customerService.updateProfile(customerId, request);
    }

    @GetMapping("/{customerId}/appointments")
    public List<AppointmentResponse> appointments(@PathVariable Long customerId) {
        return appointmentService.listForCustomer(customerId);
    }

    @GetMapping("/{customerId}/reviews")
    public List<ReviewResponse> reviews(@PathVariable Long customerId) {
        return reviewService.listForCustomer(customerId);
    }

    @PostMapping("/{customerId}/appointments")
    public ResponseEntity<AppointmentResponse> bookAppointment(
            @PathVariable Long customerId,
            @Valid @RequestBody BookRequest request) {
        AppointmentResponse appointment = appointmentService.book(customerId, request);
        return ResponseEntity.created(URI.create("/api/customers/" + customerId
                + "/appointments/" + appointment.id())).body(appointment);
    }
   
    @PostMapping("/{customerId}/appointments/{appointmentId}/review")
    public ResponseEntity<ReviewResponse> reviewAppointment(
            @PathVariable Long customerId,
            @PathVariable Long appointmentId,
            @Valid @RequestBody ReviewRequest request) {
        ReviewResponse review = reviewService.create(customerId, appointmentId, request);
        return ResponseEntity.created(URI.create("/api/trainers/" + review.trainerId()
                + "/reviews/" + review.id())).body(review);
    }
}
