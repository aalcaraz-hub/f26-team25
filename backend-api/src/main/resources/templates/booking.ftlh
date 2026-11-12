<#import "layout.ftlh" as layout>
<@layout.page title="SalonSearcher - Book an Appointment">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SalonSearcher - Book Appointment</title>
    <link rel="stylesheet" href="/styles.css">
  </head>
    <main class="booking-container">
        <div class="booking-card">
            <h1>Book an Appointment</h1>
            <p class="subtitle">Choose a service, date, and available time.</p>
            <div class="provider">
                <h2>Heavenly Hair Studio</h2>
                <p>Greensboro, NC</p>
            </div>
    <form action="/booking" method="post">
        <div class="form-group">
            <label for="service">Choose a service</label>
            <select id="service" name="beautyServiceId" required>
                <option value="1">Blowout - $55</option>
                <option value="2">Haircut - $60+</option>
                <option value="3">Hair Color - $300+</option>
            </select>
        </div>
        <div class="form-group">
            <label for="date">Choose a date</label>
            <select id="date" name="date" required>
                <option value="1">November 10, 2026</option>
                <option value="2">November 11, 2026</option>
                <option value="3">November 12, 2026</option>
            </select>
        </div>
        <div class="form-group">
            <label for="timeslot">Choose an available time</label>
            <select id="timeslot" name="timeslotId" required>
                <option value="1">9:00 AM</option>
                <option value="2">10:30 AM</option>
                <option value="3">12:00 PM</option>
                <option value="4">1:30 PM</option>
                <option value="5">3:00 PM</option>
                <option value="6">4:30 PM</option>
            </select>
        </div>
        <div class="form-group">
            <label for="notes">Additional notes (optional)</label>
            <textarea id="notes" name="notes"></textarea>
        </div>
        <input type="hidden" name="level" value="">
        <input type="hidden" name="location" value="Heavenly Hair Studio, Greensboro, NC">
        <button type="submit" class="confirm-button">Confirm Appointment</button>
    </form>
    </div>
    </main>
</@layout.page>