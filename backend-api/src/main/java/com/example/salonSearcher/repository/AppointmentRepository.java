package com.example.salonSearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.salonSearcher.entity.Appointment;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByCustomerIdOrderByStartTimeAsc(Long customerId);
    List<Appointment> findAllByProviderIdOrderByStartTimeAsc(Long providerId);
}
