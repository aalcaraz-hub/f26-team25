package com.example.salonSearcher.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 2000)
    @Column(length = 2000)
    private String notes;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String status;

    @Size(max = 50)
    @Column(length = 50)
    private String level;

    @Size(max = 255)
    @Column(length = 255)
    private String location;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "beauty_service_id", nullable = false)
    private BeautyService beautyService;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "timeslot_id", nullable = true, unique = true)
    private Timeslot timeslot;

    @OneToOne(mappedBy = "appointment", fetch = FetchType.LAZY)
    @JsonIgnore
    private Review review;

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BeautyService getBeautyService() {
        return beautyService;
    }

    public void setBeautyService(BeautyService beautyService) {
        this.beautyService = beautyService;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        if (this.timeslot != null && this.timeslot.getAppointment() == this) {
            this.timeslot.setAppointment(null);
        }
        this.timeslot = timeslot;
        if (timeslot != null && timeslot.getAppointment() != this) {
            timeslot.setAppointment(this);
        }
    }

    /** Derived: the provider is whoever offers the booked beauty service. */
    @JsonIgnore
    public Provider getProvider() {
        return beautyService != null ? beautyService.getProvider() : null;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
