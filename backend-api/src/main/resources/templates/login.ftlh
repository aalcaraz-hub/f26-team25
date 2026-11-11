<#import "layout.ftlh" as layout>
<@layout.page title="Create Account" bodyClass="login-page">

    <div class="container">
        <h1>Create Your Account</h1>
        <p class="subtitle">Join SalonSearcher to find beauty providers and book appointments</p>
        <div class="card">

            <#if successMessage??>
                <p class="message">${successMessage}</p>
            </#if>
            <#if errorMessage??>
                <p class="message">${errorMessage}</p>
            </#if>

            <form action="/customers" method="POST" onsubmit="return validatePassword()">
                <label for="name">Full Name</label>
                <input type="text" id="name" name="name" placeholder="Enter your full name">
                <label for="phone">Phone Number</label>
                <input type="tel" id="phone" name="phoneNumber" placeholder="Enter your phone number">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter your email address">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Create a password" required minlength="8" maxlength="72">
                <label for="confirmPassword">Confirm Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password" required minlength="8" maxlength="72">
                <button class="create-btn" type="submit">Create Account</button>
                <p class="message">Your account will allow you to manage your profile, browse providers, book appointments, and leave reviews</p>

            </form>

        </div>
    </div>
    <script>
        function validatePassword() {
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("confirmPassword").value;
            if (password !== confirmPassword) {
                alert("Passwords do not match");
                return false;
            }
            return true;
        }
    </script>
</@layout.page>