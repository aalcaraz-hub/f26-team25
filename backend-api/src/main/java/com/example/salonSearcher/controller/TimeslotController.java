package com.example.salonSearcher.controller;

import com.example.salonSearcher.dto.TimeslotRequest;
import com.example.salonSearcher.dto.TimeslotResponse;
import com.example.salonSearcher.service.TimeslotService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/providers/{providerId}/timeslots")
public class TimeslotController {

    private final TimeslotService timeslotService;

    public TimeslotController(TimeslotService timeslotService) {
        this.timeslotService = timeslotService;
    }

    @PostMapping
    public ResponseEntity<TimeslotResponse> create(@PathVariable Long providerId,
                                                   @Valid @RequestBody TimeslotRequest request) {
        TimeslotResponse created = timeslotService.create(providerId, request);
        return ResponseEntity.created(URI.create(
                "/api/providers/" + providerId + "/timeslots/" + created.id())).body(created);
    }

    /** GET .../timeslots or GET .../timeslots?availableOnly=true */
    @GetMapping
    public List<TimeslotResponse> list(@PathVariable Long providerId,
                                       @RequestParam(defaultValue = "false") boolean availableOnly) {
        return timeslotService.list(providerId, availableOnly);
    }

    @PutMapping("/{timeslotId}")
    public TimeslotResponse update(@PathVariable Long providerId,
                                   @PathVariable Long timeslotId,
                                   @Valid @RequestBody TimeslotRequest request) {
        return timeslotService.update(providerId, timeslotId, request);
    }

    @DeleteMapping("/{timeslotId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long providerId, @PathVariable Long timeslotId) {
        timeslotService.delete(providerId, timeslotId);
    }
}
