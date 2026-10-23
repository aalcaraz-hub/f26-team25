package com.example.salonSearcher.service;

import com.example.salonSearcher.dto.ProviderRequest;
import com.example.salonSearcher.dto.ProviderResponse;
import com.example.salonSearcher.dto.ProviderStatisticsResponse;
import com.example.salonSearcher.dto.ProviderUpdateRequest;
import com.example.salonSearcher.entity.Provider;
import com.example.salonSearcher.repository.AppointmentRepository;
import com.example.salonSearcher.repository.ProviderRepository;
import com.example.salonSearcher.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final AppointmentRepository appointmentRepository;
    private final ReviewRepository reviewRepository;

    public ProviderService(ProviderRepository providerRepository,
                           AppointmentRepository appointmentRepository,
                           ReviewRepository reviewRepository) {
        this.providerRepository = providerRepository;
        this.appointmentRepository = appointmentRepository;
        this.reviewRepository = reviewRepository;
    }

    public ProviderResponse create(ProviderRequest req) {
        if (providerRepository.existsByEmail(req.email())) {
            throw new ConflictException("A provider with email " + req.email() + " already exists.");
        }
        Provider p = new Provider();
        p.setName(req.name());
        p.setEmail(req.email());
        p.setPhoneNumber(req.phoneNumber());
        p.setPassword(req.password()); // TODO: hash before storing if you add Spring Security
        p.setAccountStatus("ACTIVE");
        p.setBusinessName(req.businessName());
        p.setAddress(req.address());
        p.setBio(req.bio());
        return ProviderResponse.from(providerRepository.save(p));
    }

    @Transactional(readOnly = true)
    public List<ProviderResponse> findAll() {
        return providerRepository.findAll().stream().map(ProviderResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public ProviderResponse findById(Long id) {
        return ProviderResponse.from(getOrThrow(id));
    }

    public ProviderResponse update(Long id, ProviderUpdateRequest req) {
        Provider p = getOrThrow(id);
        if (req.name() != null) p.setName(req.name());
        if (req.phoneNumber() != null) p.setPhoneNumber(req.phoneNumber());
        if (req.accountStatus() != null) p.setAccountStatus(req.accountStatus());
        if (req.businessName() != null) p.setBusinessName(req.businessName());
        if (req.address() != null) p.setAddress(req.address());
        if (req.bio() != null) p.setBio(req.bio());
        return ProviderResponse.from(providerRepository.save(p));
    }

    public void delete(Long id) {
        Provider p = getOrThrow(id);
        if (appointmentRepository.countByBeautyServiceProviderId(id) > 0) {
            throw new ConflictException("Provider " + id + " still has appointments and cannot be deleted.");
        }
        providerRepository.delete(p);
    }

    @Transactional(readOnly = true)
    public ProviderStatisticsResponse getStatistics(Long id) {
        getOrThrow(id);
        Map<String, Long> byStatus = new LinkedHashMap<>();
        for (Object[] row : appointmentRepository.countByStatusForProvider(id)) {
            byStatus.put((String) row[0], (Long) row[1]);
        }
        return new ProviderStatisticsResponse(
                id,
                appointmentRepository.countByBeautyServiceProviderId(id),
                appointmentRepository.countDistinctCustomersForProvider(id),
                byStatus,
                reviewRepository.countByProviderId(id),
                reviewRepository.averageRatingForProvider(id));
    }

    private Provider getOrThrow(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Provider " + id + " was not found."));
    }
}
