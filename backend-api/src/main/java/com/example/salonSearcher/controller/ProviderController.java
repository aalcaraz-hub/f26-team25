package com.example.salonSearcher.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.salonSearcher.dto.ProviderRequest;
import com.example.salonSearcher.dto.ProviderResponse;
import com.example.salonSearcher.dto.ProviderStatisticsResponse;
import com.example.salonSearcher.dto.ProviderUpdateRequest;
import com.example.salonSearcher.dto.ReviewReplyRequest;
import com.example.salonSearcher.dto.ReviewResponse;
import com.example.salonSearcher.service.ProviderService;
import com.example.salonSearcher.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

    private final ProviderService providerService;
    private final ReviewService reviewService;

    public ProviderController(ProviderService providerService, ReviewService reviewService) {
        this.providerService = providerService;
        this.reviewService = reviewService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProviderResponse create(@Valid @RequestBody ProviderRequest request) {
        return providerService.create(request);
    }

    @GetMapping
    public List<ProviderResponse> getAll() {
        return providerService.findAll();
    }

    @GetMapping("/{id}")
    public ProviderResponse getById(@PathVariable Long id) {
        return providerService.findById(id);
    }

    @PutMapping("/{id}")
    public ProviderResponse update(@PathVariable Long id, @Valid @RequestBody ProviderUpdateRequest request) {
        return providerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        providerService.delete(id);
    }

    @GetMapping("/{id}/statistics")
    public ProviderStatisticsResponse getStatistics(@PathVariable Long id) {
        return providerService.getStatistics(id);
    }

    @GetMapping("/{providerId}/reviews")
    public List<ReviewResponse> getReviews(@PathVariable Long providerId) {
        return reviewService.listForProvider(providerId);
    }

    @PostMapping("/{providerId}/reviews/{reviewId}/replies")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse reply(
            @PathVariable Long providerId,
            @PathVariable Long reviewId,
            @Valid @RequestBody ReviewReplyRequest request) {
        return reviewService.reply(providerId, reviewId, request);
    }
}
