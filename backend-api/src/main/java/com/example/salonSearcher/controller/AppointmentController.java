package com.example.salonSearcher.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.salonSearcher.dto.AppointmentResponse;
import com.example.salonSearcher.dto.AppointmentUpdateRequest;
import com.example.salonSearcher.service.AppointmentService;

import jakarta.validation.Valid;

/** Booking and a customer's own list live in CustomerController (/api/customers/{id}/appointments). */
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<AppointmentResponse> getAll(
            @RequestParam(required = false) Long providerId,
            @RequestParam(required = false) String status) {
        return appointmentService.findAll(providerId, status);
    }

    @GetMapping("/{id}")
    public AppointmentResponse getById(@PathVariable Long id) {
        return appointmentService.findById(id);
    }

    @PutMapping("/{id}")
    public AppointmentResponse update(@PathVariable Long id, @Valid @RequestBody AppointmentUpdateRequest request) {
        return appointmentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        appointmentService.delete(id);
    }
}
