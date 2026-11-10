package com.example.salonSearcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.salonSearcher.dto.CustomerRegistrationRequest;
import com.example.salonSearcher.dto.CustomerResponse;
import com.example.salonSearcher.dto.CustomerUpdateRequest;
import com.example.salonSearcher.service.CustomerService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class CustomerUIController {

    private final CustomerService customerService;

    public CustomerUIController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("customer", new CustomerRegistrationRequest("", "", "", ""));
        return "login";  
    }

    @PostMapping("/register")
    public String registerCustomer(@Valid @ModelAttribute("customer") CustomerRegistrationRequest request, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please correct the errors in the form.");

            return "redirect:/register";
        }
        try {
            CustomerResponse customer = customerService.register(request);
            
            redirectAttributes.addFlashAttribute("successMessage", "Your account has been created successfully!");
            redirectAttributes.addFlashAttribute("customerId", customer.id());
            redirectAttributes.addFlashAttribute("customer", customer);

            return "redirect:/profile";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/profile")
    public String showProfile(Model model){
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@RequestParam Long customerId, @Valid @ModelAttribute CustomerUpdateRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please correct the errors in the form.");
            return "redirect:/profile";
        }
        try {
            CustomerResponse customer = customerService.updateProfile(customerId, request);
            redirectAttributes.addFlashAttribute("successMessage", "Your profile has been updated successfully!");
            redirectAttributes.addFlashAttribute("customer", customer);
            return "redirect:/profile";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/profile";
        }
    }
}
