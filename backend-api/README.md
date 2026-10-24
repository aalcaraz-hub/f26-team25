# SalonSearcher Backend API

Spring Boot + JPA + PostgreSQL (Neon) API for the SalonSearcher team project, deployed on Render with Docker.

**Live URL:** https://f26-team25.onrender.com

> **Cold-start note:** the Render free instance sleeps when idle. The first request can take up to a minute. Open the URL (or send a `GET /api/providers`) once before using or demoing the API.

Example: `GET https://f26-team25.onrender.com/api/providers`

## UML class diagram

```mermaid
classDiagram
    class Customer {
        Long id
        String name
        String email
        String phoneNumber
        String accountStatus
    }
    class Provider {
        Long id
        String name
        String email
        String phoneNumber
        String accountStatus
        String businessName
        String address
        String bio
    }
    class BeautyService {
        Long id
        String name
        String description
        BigDecimal price
        Integer durationMinutes
    }
    class Timeslot {
        Long id
        LocalDateTime startTime
        LocalDateTime endTime
        Boolean isAvailable
    }
    class Appointment {
        Long id
        String status
        String notes
        String level
        String location
    }
    class Review {
        Long id
        Integer rating
        String comments
        String replyText
        LocalDateTime reviewDate
    }

    Provider "1" --> "0..*" BeautyService : offers
    Provider "1" --> "0..*" Timeslot : publishes
    Provider "1" --> "0..*" Review : receives
    Customer "1" --> "0..*" Appointment : books
    Customer "1" --> "0..*" Review : writes
    BeautyService "1" --> "0..*" Appointment : booked in
    Timeslot "1" --> "0..1" Appointment : reserved by
    Appointment "1" --> "0..1" Review : reviewed in
```

## How to run locally

```
$env:SPRING_DATASOURCE_URL="jdbc:postgresql://<host>/<db>?sslmode=require"    # PowerShell
./mvnw spring-boot:run                                                         # .\mvnw.cmd on Windows
```

The database connection string is supplied through environment variables only and is never committed to the repository.

## API endpoints

Base path: `/api`. Errors: invalid input returns **400**, a missing ID returns **404**, and a conflicting state returns **409**.

### Provider (use cases: manage provider profile, view statistics, reply to reviews)

| Method | Path | Description | Success | Implemented by |
|---|---|---|---|---|
| POST | `/api/providers` | Create provider profile | 201 | jnolte |
| GET | `/api/providers` | List providers | 200 | jnolte |
| GET | `/api/providers/{id}` | View one provider | 200 | jnolte |
| PUT | `/api/providers/{id}` | Modify provider profile | 200 | jnolte |
| DELETE | `/api/providers/{id}` | Delete provider (409 if it has appointments) | 204 | jnolte |
| GET | `/api/providers/{id}/statistics` | View customer statistics | 200 | jnolte |
| GET | `/api/providers/{providerId}/reviews` | View reviews of a provider | 200 | jnolte |
| POST | `/api/providers/{providerId}/reviews/{reviewId}/replies` | Reply to a review | 200 | jnolte |

### Beauty service (use cases: create a service, view available services)

| Method | Path | Description | Success | Implemented by |
|---|---|---|---|---|
| POST | `/api/services` | Create a service for a provider | 201 | jnolte |
| GET | `/api/services` | View available services (optional `?providerId=1`) | 200 | jnolte |
| GET | `/api/services/{id}` | View one service | 200 | jnolte |
| PUT | `/api/services/{id}` | Modify a service | 200 | jnolte |
| DELETE | `/api/services/{id}` | Delete a service (409 if it has appointments) | 204 | jnolte |

### Appointment (use cases: book an appointment, manage appointments)

| Method | Path | Description | Success | Implemented by |
|---|---|---|---|---|
| POST | `/api/customers/{customerId}/appointments` | Book a service in an available timeslot | 201 | jnolte (booking logic), Alexis (route) |
| GET | `/api/appointments` | List appointments (optional `?providerId=1&status=BOOKED`) | 200 | jnolte |
| GET | `/api/appointments/{id}` | View one appointment | 200 | jnolte |
| PUT | `/api/appointments/{id}` | Update status, notes, level or location (status `CANCELLED` frees the timeslot) | 200 | jnolte |
| DELETE | `/api/appointments/{id}` | Delete an appointment and free its timeslot | 204 | jnolte |

### Customer, timeslot and review endpoints

Lexi add your endpoints and user-story mappings here.

## Example request bodies

Objects are linked by ID (for example `providerId`), not by nesting whole objects.

**POST `/api/providers`**
```json
{
  "name": "Glow Studio",
  "email": "glow@example.com",
  "password": "secret123",
  "phoneNumber": "555-1234",
  "businessName": "Glow Studio",
  "address": "1 Main St",
  "bio": "Hair and nails"
}
```

**PUT `/api/providers/{id}`** (all fields optional; only the ones sent are changed)
```json
{
  "businessName": "Glow Studio Deluxe",
  "bio": "Hair, nails and lashes"
}
```

**POST `/api/services`**
```json
{
  "name": "Haircut",
  "description": "Cut and style",
  "price": 45.00,
  "durationMinutes": 60,
  "providerId": 1
}
```

**PUT `/api/services/{id}`**
```json
{
  "name": "Haircut Deluxe",
  "description": "Cut, wash and style",
  "price": 55.00,
  "durationMinutes": 75
}
```

**POST `/api/customers/{customerId}/appointments`**
```json
{
  "beautyServiceId": 1,
  "timeslotId": 1,
  "notes": "First visit",
  "level": "Beginner",
  "location": "Salon"
}
```

**PUT `/api/appointments/{id}`** (all fields optional)
```json
{
  "status": "COMPLETED",
  "notes": "Arrived on time"
}
```

**POST `/api/providers/{providerId}/reviews/{reviewId}/replies`**
```json
{
  "replyText": "Thank you, see you again!"
}
```

## Notes

- Passwords are stored as plain text and are never returned in responses.
- A timeslot can be booked once; booking an unavailable timeslot returns 409.
- Data persists in Neon PostgreSQL.
(`ddl-auto=update`).
