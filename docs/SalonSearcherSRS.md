# Requirements – SalonSearcher

**Project Name:** SalonSearcher
**Team:** Alexis Alcaraz (Customer) | Jordan Nolte (Provider)
**Course:** CSC 340
**Version:** 1.0
**Date:** 2026-09-16

---

## 1. Overview
**Vision.** SalonSearcher is a platform for adults to discover local beauty professionals offering services that align with their preferred style, budget, and expectations. The goal is to make it easier for customers to find, compare, and book beauty services while helping local providers connect with new clientele.

**Glossary** Terms used in the project
- **Service Provider:** The beauty professional who offers services to customers through SalonSearcher.
- **Customer:** A person seeking out professional beauty services such as hair, waxing, lashes, etc.
- **Profile:** A collection of information about a user, including contact information, preferences, and past booking history.
- **Services:** The specific beauty service offered by a provider such as nails, facials, etc.
- **Booking:** A scheduled appointment between a customer and service provider for a specified service.

**Primary Users / Roles.**
- **Customer Find service providers aligned with personal preferences, budget, and ratings.
- **Provider Attract target clientele, manage schedule, and add/edit services offered.

**Scope (this semester).**
- User profiles (customers and service providers)
- Search and compare professionals by personal preference
- Book appointments/View appointment details
- Leave reviews/View ratings

**Out of scope (deferred).**
- Salon education classes
- Waitlist for fully booked providers

> This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

---

## 2. Functional Requirements (User Stories)

### 2.1 Customer Stories
- **US‑1 — Register and manage profile**  
  _Story:_ As a customer, I want to create a SalonSearcher account so that I can view local beauty professionals.
  _Acceptance:_
  ```gherkin
  Scenario: Register with valid credentials
    Given I do not have an existing profile
    When  I provide valid registration details
    Then  I will be successfully registered and logged in
    And   I can view/manage my profile
  ```

- **US‑2 — Browse service providers by services offered**  
  _Story:_ As a customer, I want to filter service providers so that I can quickly find matches that align with my desired , services, ratings, and budget
  _Acceptance:_
  ```gherkin
  Scenario: Browse service providers by services offered
    Given:  I am logged in a customer
    When    I filter between services, rating, and budget
    Then    I should see a list of available providers that offer what I'm looking for
  ```

  - **US‑3 — Book an appointment**  
  _Story:_ As a customer, I want to book an appointment with a service provider so that I can receive my desired services.
  _Acceptance:_
  ```gherkin
  Scenario: Book an appointment
    Given:  I am logged in as a customer
    When    I select my desired provider an choose an available time slot for the service
    Then    I should receive a confirmation of the booked appointment
    And     I can view all upcoming appointment details on my dashboard
  ```
  - **US‑4 — Leave a review after a completed appointment**  
  _Story:_ As a customer, I want to leave a review after an appointment so that I can share my experience with others.
  _Acceptance:_
  ```gherkin
  Scenario: Leave a review after a completed appointment
    Given:  I am logged in a customer and I have finished an appointment with a service provider
    When    I submit a review following my appointment
    Then    The review should be saved and visible to other customers and my service provider
  ```

### 2.2 Provider Stories

- **US‑1 — Create and customize profile**  
  _Story:_ As a provider, I want to create and customize my profile so that customers can learn about my business and the services I offer.
  _Acceptance:_
```gherkin
  Scenario: Create and customize provider profile
    Given I am a registered provider
    When  I fill in my business details and save my profile
    Then  my updated profile should be saved
    And   customers should be able to view my profile information
```

- **US‑2 — Respond to reviews**  
  _Story:_ As a provider, I want to respond to my reviews so that I can engage with customer feedback and build trust with future clients.
  _Acceptance:_
```gherkin
  Scenario: Respond to a customer review
    Given I am logged in as a provider and have received a customer review
    When  I submit a response to the review
    Then  my response should be saved and displayed alongside the review
```

- **US‑3 — Manage services and pricing**  
  _Story:_ As a provider, I want to add, edit, and remove the services I offer along with their prices so that customers see accurate offerings when browsing my profile.
  _Acceptance:_
```gherkin
  Scenario: Add a new service
    Given I am logged in as a provider
    When  I enter a new service name, description, price, and duration
    Then  the service should be added to my list of offered services
    And   customers should be able to view it on my profile
```

- **US‑4 — Manage availability**  
  _Story:_ As a provider, I want to set and update my availability so that customers can view accurate open time slots when booking an appointment.
  _Acceptance:_
```gherkin
  Scenario: Update weekly availability
    Given I am logged in as a provider
    When  I set my available days and hours and save
    Then  my availability should be updated
    And   customers should see my current availability when searching for an appointment
```
## 3. Non‑Functional Requirements (make them measurable)
- **Performance:** description 
- **Availability/Reliability:** description
- **Security/Privacy:** description
- **Usability:** description

---

## 4. Assumptions, Constraints, and Policies
- list any rules, policies, assumptions, etc.

---

## 5. Milestones (course‑aligned)
- **M1 Requirements** — this file + stories opened as issues. 
- **M2 High‑fidelity prototype** — core customer/provider flows fully interactive. 
- **M3 Design** — architecture, schema, API outline. 
- **M4 Backend API** — key endpoints + tests. 
- **M5 Increment** — ≥2 use cases end‑to‑end. 
- **M6 Final** — complete system & documentation. 

---

## 6. Change Management
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.
