package com.example.salonSearcher.dto;

import java.time.LocalDateTime;
import com.example.salonSearcher.entity.Appointment;
import com.example.salonSearcher.entity.Timeslot;

public record AppointmentResponse(
        Long id,
        String notes,
        String status,
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

    public static AppointmentResponse from(Appointment appointment) {
        Timeslot timeslot = appointment.getTimeslot();
        return new AppointmentResponse(appointment.getId(), appointment.getNotes(), appointment.getStatus(),
                appointment.getLevel(), appointment.getLocation(), appointment.getCustomer().getId(),
                appointment.getCustomer().getName(), appointment.getBeautyService().getId(),
                appointment.getBeautyService().getName(), appointment.getProvider().getId(),
                appointment.getProvider().getName(),
                timeslot == null ? null : timeslot.getId(),
                timeslot == null ? null : timeslot.getStartTime(),
                timeslot == null ? null : timeslot.getEndTime());
    }
}
