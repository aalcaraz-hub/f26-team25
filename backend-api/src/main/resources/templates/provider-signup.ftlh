<#import "layout.ftlh" as layout>
<@layout.page title="Provider Sign Up">
    <main class="container">
        <h1>Create Your Provider Account</h1>
        <p class="subtitle">Join SalonSearcher to showcase your services and get discovered by new customers</p>
        <#if errorMessage??>
            <p class="message" style="color: #b00020;">${errorMessage}</p>
        </#if>
        <section class="card">
            <form action="/provider/signup" method="POST">
                <div class="form-group">
                    <label for="business">Business Name</label>
                    <input type="text" id="business" name="businessName" placeholder="Enter your business name" required>
                </div>
                <div class="form-group">
                    <label for="name">Owner Full Name</label>
                    <input type="text" id="name" name="name" placeholder="Enter your full name" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email" required>
                </div>
                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="tel" id="phone" name="phoneNumber" placeholder="Enter your phone number" required>
                </div>
                <div class="form-group">
                    <label for="address">Location</label>
                    <input type="text" id="address" name="address" placeholder="City, State">
                </div>
                <div class="form-group">
                    <label for="bio">About Your Business</label>
                    <textarea id="bio" name="bio" placeholder="Tell customers about your services"></textarea>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Create a password" required>
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password" required>
                </div>
                <button class="save-button" type="submit">Create Provider Account</button>
            </form>
        </section>
    </main>
</@layout.page>
