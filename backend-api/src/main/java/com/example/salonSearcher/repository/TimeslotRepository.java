package com.example.salonSearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.salonSearcher.entity.Timeslot;

import java.util.List;
    
public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {
    List<Timeslot> findAllByProviderIdOrderByStartTimeAsc(Long providerId);
    List<Timeslot> findAllByProviderIdAndIsAvailableTrueOrderByStartTimeAsc(Long providerId);
}
