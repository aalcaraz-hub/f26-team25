package com.example.salonSearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.salonSearcher.dto.CustomerRegistrationRequest;
import com.example.salonSearcher.dto.CustomerResponse;
import com.example.salonSearcher.dto.CustomerUpdateRequest;
import com.example.salonSearcher.service.CustomerService;
import com.example.salonSearcher.dto.ReviewRequest;
import com.example.salonSearcher.service.ReviewService;
import com.example.salonSearcher.service.AppointmentService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class CustomerUIController {

    private final CustomerService customerService;
    private final ReviewService reviewService;
    @Autowired 
    private final AppointmentService appointmentService;

    public CustomerUIController(CustomerService customerService, ReviewService reviewService, AppointmentService appointmentService) {
        this.customerService = customerService;
        this.reviewService = reviewService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/customers")
    public String showRegistrationForm(Model model){
        model.addAttribute("customer", new CustomerRegistrationRequest("", "", "", ""));
        return "login";  
    }

    @PostMapping("/customers")
    public String registerCustomer(@Valid @ModelAttribute("customer") CustomerRegistrationRequest request, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpSession session){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please correct the errors in the form.");

            return "redirect:/customers";
        }
        try {
            CustomerResponse customer = customerService.register(request);
            session.setAttribute("customerId", customer.id());
            session.setAttribute("customer", customer);
            
            redirectAttributes.addFlashAttribute("successMessage", "Your account has been created successfully!");
            redirectAttributes.addFlashAttribute("customerId", customer.id());
            redirectAttributes.addFlashAttribute("customer", customer);

            return "redirect:/profile";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/customers";
        }
    }

    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session){
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/customers";
        }
        model.addAttribute("customer", session.getAttribute("customer"));
        model.addAttribute("appointments", appointmentService.listForCustomer(customerId));
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@RequestParam Long customerId, @Valid @ModelAttribute CustomerUpdateRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please correct the errors in the form.");
            return "redirect:/profile";
        }
        try {
            CustomerResponse updatedCustomer = customerService.updateProfile(customerId, request);
            redirectAttributes.addFlashAttribute("successMessage", "Your profile has been updated successfully!");
            redirectAttributes.addFlashAttribute("customer", updatedCustomer);
            session.setAttribute("customer", updatedCustomer);
            return "redirect:/profile";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/profile";
        }
    }

    @GetMapping("/")
    public String showHome(){
        return "index";
    }

    @GetMapping("/browse")
    public String showBrowse(){
        return "browse";
    }
    
    @GetMapping("/provider-hair")
    public String showProviderHair(){
        return "provider-hair";
    }

    @GetMapping("/provider-nails")
    public String showProviderNails(){
        return "provider-nails";
    }

    @GetMapping("/provider-skin")
    public String showProviderSkin(){
        return "provider-skin";
    }
    
    @GetMapping("/review")
    public String showReviewPage(@RequestParam Long customerId, @RequestParam Long appointmentId, Model model){
        model.addAttribute("customerId", customerId);
        model.addAttribute("appointmentId", appointmentId);
        return "review";
    }

    @PostMapping("/review")
    public String submitReview(@RequestParam Long customerId, @RequestParam Long appointmentId, @RequestParam Integer rating, @RequestParam(required = false) String comments, RedirectAttributes redirectAttributes){
        ReviewRequest request = new ReviewRequest(rating, comments);
        try {
            reviewService.create(customerId, appointmentId, request);
            redirectAttributes.addFlashAttribute("successMessage", "Your review has been submitted successfully!");
            return "redirect:/profile";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/profile";
        }
    }
}