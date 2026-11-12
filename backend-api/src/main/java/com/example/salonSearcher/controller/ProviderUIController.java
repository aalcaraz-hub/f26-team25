package com.example.salonSearcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.salonSearcher.dto.ProviderRequest;
import com.example.salonSearcher.dto.ProviderResponse;
import com.example.salonSearcher.dto.ProviderUpdateRequest;
import com.example.salonSearcher.service.ProviderService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Server-side (FreeMarker) pages for the Provider actor.
 * Story 1: a provider creates an account and edits their profile.
 * This controller only handles web pages; all business rules stay in ProviderService.
 */
@Controller
@RequestMapping("/provider")
public class ProviderUIController {

    private final ProviderService providerService;

    public ProviderUIController(ProviderService providerService) {
        this.providerService = providerService;
    }

    // ---------- Sign up ----------

    @GetMapping("/signup")
    public String showSignupForm() {
        return "provider-signup";
    }

    @PostMapping("/signup")
    public String signUp(@Valid @ModelAttribute ProviderRequest request,
                         BindingResult bindingResult,
                         @RequestParam(required = false, defaultValue = "") String confirmPassword,
                         RedirectAttributes redirectAttributes,
                         HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Please fill in every required field with a valid value.");
            return "redirect:/provider/signup";
        }
        if (!confirmPassword.equals(request.password())) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "The passwords you entered do not match.");
            return "redirect:/provider/signup";
        }
        try {
            ProviderResponse provider = providerService.create(request);
            // Remember who is logged in. Only the id is stored; the profile page reloads the data from the database.
            session.setAttribute("providerId", provider.id());
            redirectAttributes.addFlashAttribute("successMessage",
                    "Your provider account has been created.");
            return "redirect:/provider/profile";
        } catch (RuntimeException e) {
            // For example: ConflictException when the email is already registered.
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/provider/signup";
        }
    }

    // ---------- Profile ----------

    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/provider/signup";
        }
        try {
            // Always read fresh data from the database so the page reflects what is really saved.
            model.addAttribute("provider", providerService.findById(providerId));
            return "provider-profile";
        } catch (RuntimeException e) {
            // For example: NotFoundException if the provider no longer exists.
            session.removeAttribute("providerId");
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/provider/signup";
        }
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid @ModelAttribute ProviderUpdateRequest request,
                                BindingResult bindingResult,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        // The id comes from the session, not from the form, so one provider cannot edit another's profile.
        Long providerId = (Long) session.getAttribute("providerId");
        if (providerId == null) {
            return "redirect:/provider/signup";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Your changes were not saved. Please check the form and try again.");
            return "redirect:/provider/profile";
        }
        try {
            providerService.update(providerId, request);
            redirectAttributes.addFlashAttribute("successMessage", "Your profile has been saved.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/provider/profile";
    }
}
