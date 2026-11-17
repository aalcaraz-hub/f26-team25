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
            <#if errorMessage??>
                <p style="color: red;">${errorMessage}</p>
            </#if>
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
                <#list beautyServices as service>
                <option value="${service.id}">${service.name} - $${service.price}</option>
                </#list>
            </select>
        </div>
        <div class="form-group">
            <label for="date">Choose a date</label>
            <input type="date" id="date" name="date" required>
        </div>
        <div class="form-group">
            <label for="timeslot">Choose an available time</label>
            <select id="timeslot" name="timeslotId" required>
                <#list timeslots as timeslot>
                <option value="${timeslot.id}" data-date="${timeslot.startTime}"> ${timeslot.formattedStartTime}</option>
                </#list>
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

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const dateInput = document.getElementById("date");
            const timeslotSelect = document.getElementById("timeslot");

            const timeOptions = Array.from(timeslotSelect.options);

            function filterTimeslots() {
                const selectedDate = dateInput.value;
                timeslotSelect.innerHTML = "";
                timeOptions.forEach(option => {
                    const slotDate = option.dataset.date.substring(0, 10);
                    if (slotDate === selectedDate) {
                        timeslotSelect.add(option.cloneNode(true));
                    }
                });
            }
            dateInput.addEventListener("change", filterTimeslots);
            filterTimeslots();
        });
    </script>
</@layout.page>