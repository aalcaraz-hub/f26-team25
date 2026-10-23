package com.example.salonSearcher.entity;

/**
 * Allowed values for Appointment.status (stored as a String in the entity).
 * Timeslot.getBookedAppointment() relies on "BOOKED"; ReviewService requires "COMPLETED".
 * To cancel, delete the appointment (that frees the timeslot).
 */
public enum AppointmentStatus {
    BOOKED,
    COMPLETED
}
