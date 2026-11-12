<#import "layout.ftlh" as layout>
<@layout.page title="My Provider Profile">
    <main class="container">
        <h1>My Provider Profile</h1>
        <#if successMessage??>
            <p class="message">${successMessage}</p>
        </#if>
        <#if errorMessage??>
            <p class="message" style="color: #b00020;">${errorMessage}</p>
        </#if>
        <section class="card">
            <div class="profile-header">
                <div class="avatar">✿</div>
                <div>
                    <h2>${provider.businessName()!''}</h2>
                    <p>Provider Account</p>
                </div>
            </div>
            <h2>Manage Profile</h2>
            <form action="/provider/profile" method="POST">
                <div class="form-group">
                    <label for="business">Business Name</label>
                    <input type="text" id="business" name="businessName" value="${provider.businessName()!''}" required>
                </div>
                <div class="form-group">
                    <label for="name">Owner Full Name</label>
                    <input type="text" id="name" name="name" value="${provider.name()!''}" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" value="${provider.email()!''}" disabled>
                </div>
                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="tel" id="phone" name="phoneNumber" value="${provider.phoneNumber()!''}">
                </div>
                <div class="form-group">
                    <label for="address">Location</label>
                    <input type="text" id="address" name="address" value="${provider.address()!''}">
                </div>
                <div class="form-group">
                    <label for="bio">About</label>
                    <textarea id="bio" name="bio">${provider.bio()!''}</textarea>
                </div>
                <button class="save-button" type="submit">Save Changes</button>
            </form>
        </section>
        <section class="card">
            <h2>My Services</h2>
            <p>Add, edit, or remove the services customers can book.</p>
            <form action="/provider/services" method="GET">
                <button class="save-button" type="submit">Manage My Services</button>
            </form>
        </section>
    </main>
</@layout.page>
