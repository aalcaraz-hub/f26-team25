<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SalonSearcher - My Profile</title>
    <link rel="stylesheet" href="/styles.css">
  </head>
  <body>
    <nav>
        <div class="logo"> <img src="/images/S.png" alt="SalonSearcher Logo"><span>SalonSearcher</span></div>
        <div>
            <a href="index.html">Home</a>
            <a href="browse.html">Find a Provider</a>
            <a href="/profile">My Profile</a>
            <a href="/register">Create Account</a>
        </div>
    </nav>
    <main class="container">
        <h1>My Profile</h1>
        <#if successMessage??>
            <p class="message">${successMessage}</p>
        </#if>

        <section class="card">
            <div class="profile-header">
                <div class="avatar">✿</div>
                <div>
                    <h2>${customer.name()}</h2>
                    <p>Customer Account</p>
                </div>
            </div>
            <h2>Manage Profile</h2>
            <form action="/profile" method="POST">
                <input type="hidden" name="customerId" value="${customer.id()}">
        
            <div class="form-group">
                <label for="name">Full Name</label>
                <input type="text" id="name" name="name" value="${customer.name()}">
            </div>
            <div class="form-group">
                <label for="phone">Phone Number</label>
                <input type="tel" id="phone" name="phoneNumber" value="${customer.phoneNumber()}">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="${customer.email()}">
            </div>
            <button class="save-button" type="submit" onclick="alert('Your profile has been saved!')">Save Changes</button>
            </form>
        </section>
        <section class="card">
            <h2>Upcoming Appointments</h2>
            <div class="appointment">
                <h3>Heavenly Hair Studio</h3>
                <p><strong>Service: </strong>Haircut</p>
                <p><strong>Date: </strong>October 3rd, 2026</p>
                <p><strong>Time: </strong>10:30 AM</p>
                <p><strong>Location: </strong>Greensboro, NC</p>
            </div>
            <div class="appointment">
                <h3>Glass Skin Spa</h3>
                <p><strong>Service: </strong>Lash Extensions</p>
                <p><strong>Date: </strong>October 11th, 2026</p>
                <p><strong>Time: </strong>4:30 PM</p>
                <p><strong>Location: </strong>Greensboro, NC</p>
            </div>
        </section>
        <section class="card">
            <h2>Past Appointments</h2>
            <div class="appointment">
                <h3>Heavenly Hair Studio</h3>
                <p><strong>Service: </strong>Hair Color</p>
                <p><strong>Date: </strong>September 19th, 2026</p>
                <p><strong>Time: </strong>12:00 PM</p>
                <p><strong>Location: </strong>Greensboro, NC</p>
                <button class="review-button" onclick="window.location.href='review.html'">Leave a Review</button>
            </div>
        </section>
    </main>
    <footer>
        <p>© 2026 SalonSearcher</p>
    </footer>
  </body>
</html>