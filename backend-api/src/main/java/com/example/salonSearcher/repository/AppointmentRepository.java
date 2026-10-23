package com.example.salonSearcher.repository;

import com.example.salonSearcher.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByCustomerIdOrderByIdDesc(Long customerId);

    long countByBeautyServiceProviderId(Long providerId);

    long countByBeautyServiceId(Long beautyServiceId);

    @Query("select count(distinct a.customer.id) from Appointment a "
            + "where a.beautyService.provider.id = :providerId")
    long countDistinctCustomersForProvider(@Param("providerId") Long providerId);

    @Query("select a.status, count(a) from Appointment a "
            + "where a.beautyService.provider.id = :providerId group by a.status")
    List<Object[]> countByStatusForProvider(@Param("providerId") Long providerId);
}
