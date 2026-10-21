package com.example.salonSearcher.service;

import com.example.salonSearcher.dto.TimeslotRequest;
import com.example.salonSearcher.dto.TimeslotResponse;
import com.example.salonSearcher.entity.Timeslot;
import com.example.salonSearcher.entity.Provider;
import com.example.salonSearcher.repository.TimeslotRepository;
import com.example.salonSearcher.repository.ProviderRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import com.example.salonSearcher.service.BadRequestException;
import com.example.salonSearcher.service.ConflictException;
import com.example.salonSearcher.service.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TimeslotService {
    private final TimeslotRepository timeslotRepository;
    private final ProviderRepository providerRepository;

    public TimeslotService(TimeslotRepository timeslotRepository, ProviderRepository providerRepository) {
        this.timeslotRepository = timeslotRepository;
        this.providerRepository = providerRepository;
    }

    public TimeslotResponse create(Long providerId, TimeslotRequest request) {
        Provider provider = findProvider(providerId);
        validateRange(request.startTime(), request.endTime());
        Timeslot timeslot = new Timeslot();
        timeslot.setProvider(provider);
        apply(timeslot, request);
        return TimeslotResponse.from(timeslotRepository.save(timeslot));
    }

    @Transactional(readOnly = true)
    public List<TimeslotResponse> list(Long providerId, boolean availableOnly) {
        if (!providerRepository.existsById(providerId)) {
            throw new NotFoundException("Provider " + providerId + " was not found.");
        }
        List<Timeslot> timeslots = availableOnly
                ? timeslotRepository.findAllByProviderIdAndIsAvailableTrueOrderByStartTime(providerId)
                : timeslotRepository.findAllByProviderIdOrderByStartTime(providerId);
        return timeslots.stream().map(TimeslotResponse::from).toList();
    }

    public TimeslotResponse update(Long providerId, Long timeslotId, TimeslotRequest request) {
        Timeslot timeslot = lockTimeslot(timeslotId);
        if (!timeslot.getProvider().getId().equals(providerId)) {
            throw new NotFoundException("Timeslot " + timeslotId + " was not found for provider "
                    + providerId + ".");
        }
        if (timeslot.getAppointment() != null) {
            throw new ConflictException("A booked timeslot cannot be edited.");
        }
        validateRange(request.startTime(), request.endTime());
        apply(timeslot, request);
        return TimeslotResponse.from(timeslotRepository.save(timeslot));
    }

    public void delete(Long providerId, Long timeslotId) {
        Timeslot timeslot = lockTimeslot(timeslotId);
        if (!timeslot.getProvider().getId().equals(providerId)) {
            throw new NotFoundException("Timeslot " + timeslotId + " was not found for provider "
                    + providerId + ".");
        }
        if (timeslot.getAppointment() != null) {
            throw new ConflictException("A booked timeslot cannot be deleted.");
        }
        timeslotRepository.delete(timeslot);
    }

    private void apply(Timeslot timeslot, TimeslotRequest request) {
        timeslot.setStartTime(request.startTime());
        timeslot.setEndTime(request.endTime());
        timeslot.setIsAvailable(request.isAvailable());
    }

    private void validateRange(LocalDateTime startTime, LocalDateTime endTime) {
        if (!endTime.isAfter(startTime)) {
            throw new BadRequestException("endTime must be after startTime.");
        }
        if (!startTime.isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Timeslots must start in the future.");
        }
    }

    private Provider findProvider(Long providerId) {
        return providerRepository.findById(providerId)
                .orElseThrow(() -> new NotFoundException("Provider " + providerId + " was not found."));
    }

    private Timeslot lockTimeslot(Long timeslotId) {
        return timeslotRepository.findByIdForUpdate(timeslotId)
                .orElseThrow(() -> new NotFoundException("Timeslot " + timeslotId + " was not found."));
    }
}