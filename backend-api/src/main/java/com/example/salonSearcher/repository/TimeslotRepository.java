package com.example.salonSearcher.repository;

import com.example.salonSearcher.entity.Timeslot;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {

    List<Timeslot> findAllByProviderIdAndIsAvailableTrueOrderByStartTime(Long providerId);

    List<Timeslot> findAllByProviderIdOrderByStartTime(Long providerId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select t from Timeslot t where t.id = :id")
    Optional<Timeslot> findByIdForUpdate(@Param("id") Long id);
}
