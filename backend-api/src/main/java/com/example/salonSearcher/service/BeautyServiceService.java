package com.example.salonSearcher.service;

import com.example.salonSearcher.dto.BeautyServiceRequest;
import com.example.salonSearcher.dto.BeautyServiceResponse;
import com.example.salonSearcher.dto.BeautyServiceUpdateRequest;
import com.example.salonSearcher.entity.BeautyService;
import com.example.salonSearcher.entity.Provider;
import com.example.salonSearcher.repository.AppointmentRepository;
import com.example.salonSearcher.repository.BeautyServiceRepository;
import com.example.salonSearcher.repository.ProviderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BeautyServiceService {

    private final BeautyServiceRepository beautyServiceRepository;
    private final ProviderRepository providerRepository;
    private final AppointmentRepository appointmentRepository;

    public BeautyServiceService(BeautyServiceRepository beautyServiceRepository,
                                ProviderRepository providerRepository,
                                AppointmentRepository appointmentRepository) {
        this.beautyServiceRepository = beautyServiceRepository;
        this.providerRepository = providerRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public BeautyServiceResponse create(BeautyServiceRequest req) {
        Provider provider = providerRepository.findById(req.providerId())
                .orElseThrow(() -> new NotFoundException("Provider " + req.providerId() + " was not found."));
        BeautyService s = new BeautyService();
        s.setProvider(provider);
        s.setName(req.name());
        s.setDescription(req.description());
        s.setPrice(req.price());
        s.setDurationMinutes(req.durationMinutes());
        return BeautyServiceResponse.from(beautyServiceRepository.save(s));
    }

    /** providerId is optional: null returns every service. */
    @Transactional(readOnly = true)
    public List<BeautyServiceResponse> list(Long providerId) {
        List<BeautyService> services = providerId == null
                ? beautyServiceRepository.findAll()
                : beautyServiceRepository.findAllByProviderId(providerId);
        return services.stream().map(BeautyServiceResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public BeautyServiceResponse findById(Long id) {
        return BeautyServiceResponse.from(getOrThrow(id));
    }

    public BeautyServiceResponse update(Long id, BeautyServiceUpdateRequest req) {
        BeautyService s = getOrThrow(id);
        s.setName(req.name());
        s.setDescription(req.description());
        s.setPrice(req.price());
        s.setDurationMinutes(req.durationMinutes());
        return BeautyServiceResponse.from(beautyServiceRepository.save(s));
    }

    public void delete(Long id) {
        BeautyService s = getOrThrow(id);
        if (appointmentRepository.countByBeautyServiceId(id) > 0) {
            throw new ConflictException("Service " + id + " has appointments and cannot be deleted.");
        }
        beautyServiceRepository.delete(s);
    }

    private BeautyService getOrThrow(Long id) {
        return beautyServiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Service " + id + " was not found."));
    }
}
