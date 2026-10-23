package com.example.salonSearcher.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A booking: one Customer books one BeautyService in one Timeslot.
 *
 *   Customer     1 -- 0..*  Appointment
 *   BeautyService 1 -- 0..*  Appointment
 *   Timeslot     1 -- 0..1  Appointment
 */
@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // BOOKED, COMPLETED or CANCELLED
    @NotBlank(message = "Status is required")
    @Column(nullable = false)
    private String status = "BOOKED";

    @Column(length = 500)
    private String notes;

    @NotNull(message = "Customer is required")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull(message = "Service is required")
    @ManyToOne(optional = false)
    @JoinColumn(name = "beauty_service_id", nullable = false)
    private BeautyService beautyService;

    // unique = true on the join column enforces that one slot holds at most one appointment.
    @NotNull(message = "Timeslot is required")
    @OneToOne(optional = false)
    @JoinColumn(name = "timeslot_id", nullable = false, unique = true)
    private Timeslot timeslot;
}
