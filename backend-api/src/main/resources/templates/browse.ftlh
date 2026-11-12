<#import "layout.ftlh" as layout>
<@layout.page title="Find a Provider">
    <section class="page-header">
        <h1>Find a Beauty Professional</h1>
        <p>Browse providers and find the right service for you.</p>
    </section>
    <div class="filters">
        <input type="text" placeholder="Search by service or provider">
        <select>
            <option>All Services</option>
            <option>Manicurists</option>
            <option>Estheticians</option>
            <option>Cosmotologists</option>
        </select>
        <select>
            <option>Any Location</option>
            <option>Within 5 miles</option>
            <option>Within 10 miles</option>
            <option>Within 25 miles</option>
        </select>
        <button class="filter-button">Search</button>
    </div>
    <section class="providers">
        <div class="provider-card">
            <img src="/images/hairsalon.jpg" alt="Heavenly Hair Studio">
            <div class="provider-info">
            <h2>Heavenly Hair Studio</h2>
            <div class="rating"> ★ ★ ★ ★ ★ 4.9</div>
            <p><strong>Services: </strong>Haircuts, Color, Blowouts</p>
            <p><strong>Location: </strong>Greensboro, NC</p>
            <button class="view-button" onclick="window.location.href='/provider-hair'">View Profile</button>
        </div>
        </div>
        <div class="provider-card">
            <img src="/images/nailsalon.png" alt="Nail'd It">
            <div class="provider-info">
            <h2>Nail'd It Salon</h2>
            <div class="rating">★ ★ ★ ★ ☆  4.3</div>
            <p><strong>Services: </strong>Gel Manicures, Nail Extensions, Basic Pedicures, Deluxe Pedicures</p>
            <p><strong>Location: </strong>Greensboro, NC</p>
            <button class="view-button"onclick="window.location.href='/provider-nails   '">View Profile</button>
            </div>
        </div>
        <div class="provider-card">
            <img src="/images/skinspa.jpg" alt="Glass Skin Spa">
            <div class="provider-info">
            <h2>Glass Skin Spa</h2>
            <div class="rating"> ★ ★ ★ ★ ★ 4.8</div>
            <p><strong>Services: </strong>Facials, Waxing, Lash Extensions</p>
            <p><strong>Location: </strong>Greensboro, NC</p>
            <button class="view-button"onclick="window.location.href='/provider-skin'">View Profile</button>
            </div>
        </div>
    </section>
</@layout.page>