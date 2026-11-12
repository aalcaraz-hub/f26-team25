package com.example.salonSearcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.salonSearcher.dto.BeautyServiceRequest;
import com.example.salonSearcher.dto.BeautyServiceResponse;
import com.example.salonSearcher.dto.BeautyServiceUpdateRequest;
import com.example.salonSearcher.service.BeautyServiceService;
import com.example.salonSearcher.service.NotFoundException;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Server-side (FreeMarker) pages for Story 2: a provider manages their own services.
 * Like ProviderUIController, this class only handles web requests and messages;
 * the rules and database work stay in BeautyServiceService.
 */
@Controller
@RequestMapping("/provider/services")
public class ProviderServicesUIController {

    private static final String FORM_ERROR =
            "Your changes were not saved. Enter a service name, and make sure price and duration are zero or more.";

    private final BeautyServiceService beautyServiceService;

    public ProviderServicesUIController(BeautyServiceService beautyServiceService) {
        this.beautyServiceService = beautyServiceService;
    }

    // ---------- List ----------

    @GetMapping
    public String showServices(Model model, HttpSession session) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/provider/signup";
        }
        // Only this provider's services, read fresh from the database.
        model.addAttribute("services", beautyServiceService.list(providerId));
        return "provider-services";
    }

    // ---------- Add ----------

    @PostMapping
    public String addService(@Valid @ModelAttribute BeautyServiceUpdateRequest form,
                             BindingResult bindingResult,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/provider/signup";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", FORM_ERROR);
            return "redirect:/provider/services";
        }
        try {
            // The provider id comes from the session, never from the form.
            beautyServiceService.create(new BeautyServiceRequest(
                    form.name(), form.description(), form.price(), form.durationMinutes(), providerId));
            redirectAttributes.addFlashAttribute("successMessage", "Service added.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/provider/services";
    }

    // ---------- Edit ----------

    @PostMapping("/{id}")
    public String updateService(@PathVariable Long id,
                                @Valid @ModelAttribute BeautyServiceUpdateRequest form,
                                BindingResult bindingResult,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/provider/signup";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", FORM_ERROR);
            return "redirect:/provider/services";
        }
        try {
            requireOwnedService(id, providerId);
            beautyServiceService.update(id, form);
            redirectAttributes.addFlashAttribute("successMessage", "Service updated.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/provider/services";
    }

    // ---------- Remove ----------

    @PostMapping("/{id}/delete")
    public String deleteService(@PathVariable Long id,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/provider/signup";
        }
        try {
            requireOwnedService(id, providerId);
            beautyServiceService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Service removed.");
        } catch (RuntimeException e) {
            // For example: ConflictException when customers have already booked this service.
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/provider/services";
    }

    /**
     * The service layer updates and deletes by id alone, so this check makes sure the
     * service belongs to the logged-in provider. An unknown id or someone else's
     * service both look like "not found".
     */
    private void requireOwnedService(Long serviceId, Long providerId) {
        BeautyServiceResponse service = beautyServiceService.findById(serviceId); // throws NotFoundException if missing
        if (!service.providerId().equals(providerId)) {
            throw new NotFoundException("Service " + serviceId + " was not found.");
        }
    }
}
