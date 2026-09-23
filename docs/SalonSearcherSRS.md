# Requirements – SalonSearcher

**Project Name:** SalonSearcher
**Team:** Jordan Nolte, Aleris Alcaraz (Team 15)
**Course:** CSC 340
**Version:** 1.0
**Date:** 2026-09-18

## 1. Overview

**Vision.** SalonSearcher is a personalized salon and spa matching platform designed to help adult women find beauty providers who align with their goals, style, and budget. The system supports customers seeking beauty and wellness services, as well as providers (manicurists, estheticians, and cosmetologists) who want to reach and manage their target clientele.

**Glossary:** Terms used in the project

- **Provider:** The beauty professional (manicurist, esthetician, or cosmetologist) who provides services to customers.
- **Customer:** A person seeking beauty or salon/spa services.
- **Profile:** A collection of information about a user, including personal details, goals, and preferences.
- **Services:** The specific beauty treatments or offerings provided by a provider.
- **Appointment:** A scheduled booking between a customer and a provider for a service.

**Primary Users and Roles:**

- **Customer** — Find beauty providers aligned with personal goals, style, and budget.
- **Provider** — Reach and manage a target clientele.
- **SysAdmin** — Maintain platform quality and security.

**Scope (this semester):**

- User profiles (customers and providers)
- Search and filter providers by style, budget, and ratings
- Booking appointments
- Card on file for payment
- Reviews and ratings
- Provider appointment history and details

**Out of scope (deferred):**

- Waitlist notifications
- Monthly membership packages
- Rewards program
- Blocking/banning profiles from booking
- Mobile (travel) services

This document is requirements-level and solution-neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

## 2. Functional Requirements (User Stories)

### 2.1 Customer Stories

**US-1 – Browse and match with providers**

*Story:* As a customer, I want to browse or be matched with beauty providers based on my desired style, budget, and ratings so that I can quickly find a relevant provider.

*Acceptance:*
```
Scenario: Browse providers by style, budget, and rating
  Given I am logged in as a customer
  When I search or filter for providers by style, budget, or rating
  Then I should see a list of providers who match my criteria
```

**US-2 – Register and manage profile**

*Story:* As a customer, I want to create an account with SalonSearcher so that I can view provider profiles and manage my own profile.

*Acceptance:*
```
Scenario: Register and manage a profile
  Given I am not registered
  When I create an account with valid details
  Then I should be able to view provider profiles
  And I can edit or delete my profile
```

**US-3 – Book an appointment**

*Story:* As a customer, after finding a matching provider, I want to book a service with that provider so that I can receive the service I need.

*Acceptance:*
```
Scenario: Book an appointment with a provider
  Given I have found a provider that matches my needs
  When I select a service and book an appointment
  Then the appointment should be confirmed
  And I can view or delete the appointment from my account
```

**US-4 – Write a review after an appointment**

*Story:* As a customer, I want to leave a review after receiving a service so that other customers can see my feedback.

*Acceptance:*
```
Scenario: Submit a review after a completed appointment
  Given I have completed an appointment with a provider
  When I submit a review for that appointment
  Then the review should be saved and visible to other customers
```

**US-5 – View provider availability and pricing**

*Story:* As a customer, I want to view a provider's availability and pricing so that I can decide which provider and service fit my schedule and budget.

*Acceptance:*
```
Scenario: View availability and pricing on a provider profile
  Given I am viewing a provider's profile
  When I look at their listed services
  Then I should see their availability and pricing information
```

**US-6 – Join a waitlist**

*Story:* As a customer, I want the option to join a waitlist when a provider is fully booked so that I can be notified if a time slot becomes available.

*Acceptance:*
```
Scenario: Opt in to a provider's waitlist
  Given a provider I want to book is fully booked
  When I opt in to the waitlist for a specific day or time
  Then I should be notified if that day or time becomes available
```

### 2.2 Provider Stories

**US-7 – Create and update profile**

*Story:* As a provider, I want to create and customize my profile so that I can present myself to potential customers.

*Acceptance:*
```
Scenario: Create and update a provider profile
  Given I do not have a profile
  When I provide my details and submit the form
  Then my profile should be created
  And the profile should be visible to customers
```

**US-8 – Manage services, pricing, and availability**

*Story:* As a provider, I want to add the services I offer, set my prices, and update my availability so that customers understand my offerings and when I can be booked.

*Acceptance:*
```
Scenario: Add and update services, pricing, and availability
  Given I am logged in as a provider
  When I add or edit my services, prices, or availability
  Then the changes should be saved and visible to customers
```

**US-9 – Respond to reviews**

*Story:* As a provider, I want to respond to reviews so that I can engage with clients and address their feedback.

*Acceptance:*
```
Scenario: Respond to a customer review
  Given I have received a review from a customer
  When I submit a response to that review
  Then my response should be saved and visible alongside the review
```

## 3. Non-Functional Requirements

- **Performance:** 95% of provider search and discovery responses should be returned in less than 2 seconds under typical load.
- **Availability/Reliability:** The system should be available 99.5% of the time, with planned maintenance windows communicated in advance.
- **Security/Privacy:** The system must implement secure authentication and authorization mechanisms. All sensitive data, including card-on-file payment information, should be encrypted in transit and at rest.
- **Usability:** New users should be able to complete registration and book an appointment within 5 minutes without external assistance.

## 4. Assumptions, Constraints, and Policies

- Modern browsers (latest Chrome/Firefox/Edge/Safari) and stable connectivity.
- Course timeline and campus infrastructure constraints apply.
- Target demographic is adult women ages 18–65 seeking beauty and salon/spa services.

## 5. Milestones (course-aligned)

- **M1** Requirements — this file and related stories opened as issues.
- **M2** High-fidelity prototype — core customer and provider UI flows are fully interactive.
- **M3** Design — architecture, schema, and API outline.
- **M4** Backend API — key endpoints and tests.
- **M5** Increment — at least 2 use cases end-to-end.
- **M6** Final — complete system and documentation.

## 6. Change Management

Stories are living artifacts; changes are tracked via repository issues and linked pull requests. Major changes should update this SRS.
