package com.example.salonSearcher.dto;

import java.time.LocalDateTime;

import com.example.salonSearcher.entity.Appointment;

public record AppointmentResponse(
        Long id,
        String status,
        String notes,
        String level,
        String location,
        Long customerId,
        String customerName,
        Long beautyServiceId,
        String beautyServiceName,
        Long providerId,
        String providerName,
        Long timeslotId,
        LocalDateTime startTime,
        LocalDateTime endTime) {

    public static AppointmentResponse from(Appointment a) {
        var slot = a.getTimeslot();
        var provider = a.getProvider();
        return new AppointmentResponse(
                a.getId(), a.getStatus(), a.getNotes(), a.getLevel(), a.getLocation(),
                a.getCustomer().getId(), a.getCustomer().getName(),
                a.getBeautyService().getId(), a.getBeautyService().getName(),
                provider != null ? provider.getId() : null,
                provider != null ? provider.getName() : null,
                slot != null ? slot.getId() : null,
                slot != null ? slot.getStartTime() : null,
                slot != null ? slot.getEndTime() : null);
    }
}
