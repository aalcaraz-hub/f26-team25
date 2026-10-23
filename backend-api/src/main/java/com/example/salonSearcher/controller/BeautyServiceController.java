package com.example.salonSearcher.controller;

import com.example.salonSearcher.dto.BeautyServiceRequest;
import com.example.salonSearcher.dto.BeautyServiceResponse;
import com.example.salonSearcher.dto.BeautyServiceUpdateRequest;
import com.example.salonSearcher.service.BeautyServiceService;
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
@RequestMapping("/api/services")
public class BeautyServiceController {

    private final BeautyServiceService beautyServiceService;

    public BeautyServiceController(BeautyServiceService beautyServiceService) {
        this.beautyServiceService = beautyServiceService;
    }

    @PostMapping
    public ResponseEntity<BeautyServiceResponse> create(@Valid @RequestBody BeautyServiceRequest request) {
        BeautyServiceResponse created = beautyServiceService.create(request);
        return ResponseEntity.created(URI.create("/api/services/" + created.id())).body(created);
    }

    /** GET /api/services or GET /api/services?providerId=1 */
    @GetMapping
    public List<BeautyServiceResponse> list(@RequestParam(required = false) Long providerId) {
        return beautyServiceService.list(providerId);
    }

    @GetMapping("/{id}")
    public BeautyServiceResponse get(@PathVariable Long id) {
        return beautyServiceService.findById(id);
    }

    @PutMapping("/{id}")
    public BeautyServiceResponse update(@PathVariable Long id,
                                        @Valid @RequestBody BeautyServiceUpdateRequest request) {
        return beautyServiceService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        beautyServiceService.delete(id);
    }
}
