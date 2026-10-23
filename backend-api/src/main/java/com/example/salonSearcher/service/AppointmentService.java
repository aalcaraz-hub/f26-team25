package com.example.salonSearcher.service;

import com.example.salonSearcher.dto.AppointmentResponse;
import com.example.salonSearcher.dto.AppointmentUpdateRequest;
import com.example.salonSearcher.dto.BookRequest;
import com.example.salonSearcher.entity.Appointment;
import com.example.salonSearcher.entity.BeautyService;
import com.example.salonSearcher.entity.Customer;
import com.example.salonSearcher.entity.Timeslot;
import com.example.salonSearcher.repository.AppointmentRepository;
import com.example.salonSearcher.repository.BeautyServiceRepository;
import com.example.salonSearcher.repository.CustomerRepository;
import com.example.salonSearcher.repository.ReviewRepository;
import com.example.salonSearcher.repository.TimeslotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final BeautyServiceRepository beautyServiceRepository;
    private final TimeslotRepository timeslotRepository;
    private final ReviewRepository reviewRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              CustomerRepository customerRepository,
                              BeautyServiceRepository beautyServiceRepository,
                              TimeslotRepository timeslotRepository,
                              ReviewRepository reviewRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.beautyServiceRepository = beautyServiceRepository;
        this.timeslotRepository = timeslotRepository;
        this.reviewRepository = reviewRepository;
    }

    /** Both filters are optional (pass null to skip). */
    @Transactional(readOnly = true)
    public List<AppointmentResponse> findAll(Long providerId, String status) {
        return appointmentRepository.findAll().stream()
                .filter(a -> providerId == null
                        || (a.getProvider() != null && providerId.equals(a.getProvider().getId())))
                .filter(a -> status == null || status.isBlank() || status.equalsIgnoreCase(a.getStatus()))
                .map(AppointmentResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public AppointmentResponse findById(Long id) {
        return AppointmentResponse.from(getOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<AppointmentResponse> listForCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new NotFoundException("Customer " + customerId + " was not found.");
        }
        return appointmentRepository.findAllByCustomerIdOrderByIdDesc(customerId)
                .stream().map(AppointmentResponse::from).toList();
    }

    public AppointmentResponse book(Long customerId, BookRequest req) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer " + customerId + " was not found."));
        BeautyService service = beautyServiceRepository.findById(req.beautyServiceId())
                .orElseThrow(() -> new NotFoundException(
                        "Beauty service " + req.beautyServiceId() + " was not found."));
        Timeslot slot = timeslotRepository.findByIdForUpdate(req.timeslotId())
                .orElseThrow(() -> new NotFoundException("Timeslot " + req.timeslotId() + " was not found."));

        if (!Boolean.TRUE.equals(slot.getIsAvailable())) {
            throw new ConflictException("Timeslot " + slot.getId() + " is no longer available.");
        }
        if (!slot.getProvider().getId().equals(service.getProvider().getId())) {
            throw new ConflictException("That timeslot belongs to a different provider than the service.");
        }

        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setBeautyService(service);
        appointment.setStatus("BOOKED");
        appointment.setNotes(req.notes());
        appointment.setLevel(req.level());
        appointment.setLocation(req.location());
        slot.setIsAvailable(false);
        appointment.setTimeslot(slot);
        return AppointmentResponse.from(appointmentRepository.save(appointment));
    }

    public AppointmentResponse update(Long id, AppointmentUpdateRequest req) {
        Appointment a = getOrThrow(id);
        if (req.notes() != null) a.setNotes(req.notes());
        if (req.level() != null) a.setLevel(req.level());
        if (req.location() != null) a.setLocation(req.location());
        if (req.status() != null && !req.status().isBlank()) {
            a.setStatus(req.status().toUpperCase());
            if ("CANCELLED".equals(a.getStatus()) && a.getTimeslot() != null) {
                a.getTimeslot().setIsAvailable(true); // free the slot again
                a.setTimeslot(null);
            }
        }
        return AppointmentResponse.from(appointmentRepository.save(a));
    }

    public void delete(Long id) {
        Appointment a = getOrThrow(id);
        if (a.getTimeslot() != null) {
            a.getTimeslot().setIsAvailable(true);
            a.setTimeslot(null);
        }
        if (a.getReview() != null) {
            reviewRepository.delete(a.getReview());
        }
        appointmentRepository.delete(a);
    }

    private Appointment getOrThrow(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Appointment " + id + " was not found."));
    }
}
