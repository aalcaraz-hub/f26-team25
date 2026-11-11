<#import "layout.ftlh" as layout>
<@layout.page title="Heavenly Hair Studio">

    <main class="profile">
        <div class="profile-card">
            <div class="profile-header">
                <div class="profile-icon">✧</div>
                <h1>Heavenly Hair Studio</h1>
                <div class="rating"> ★ ★ ★ ★ ★ 4.9</div>
                <p class="location">Greensboro, NC</p>  
        </div>
            <div class="section">
                <h2>About</h2>
                <p>Heavenly Hair Studio is a welcoming salon dedicated to helping every client look and feel their best. Our team of experienced stylists offer professional haircuts, blowouts, and color services tailored to each client's style and preferences. We take pride in our customer's experience and would love to give you the hair of your dreams!</p>
            </div>
             <div class="section">
                <h2>Services</h2>
                <div class="service">
                    <div class="service-info">
                        <h3>Blowout</h3>
                        <p>Includes a wash, deep-conditioning treatment, and a blowout</p>
                        </div>
                        <div class="price">$55</div>
                </div>
                <div class="service">
                    <div class="service-info">
                        <h3>Haircut</h3>
                        <p>Includes a wash, haircut of desired style, and a blowdry. *Final price may vary depending on length of hair*</p>
                        </div>
                        <div class="price">$60+</div>
                </div>
                <div class="service">
                    <div class="service-info">
                        <h3>Hair Color</h3>
                        <p>Includes a wash, custom coloring, and styling. *Final price may vary depending on amount of color used*</p>
                        </div>
                        <div class="price">$300+</div>
                </div>
                <button class="book-button" onclick="window.location.href='book-hair.html'">Book an Appointment</button>
            </div>
        </div>
    </main>
</@layout.page>