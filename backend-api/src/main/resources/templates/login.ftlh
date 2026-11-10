<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SalonSearcher - Create Account</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        body {
            font-family: Arial, sans-serif;
            background-color: #fff8f6;
            color: #333;
        }
         nav {
            background-color: #ffffff;
            padding: 18px 8%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
        }
        .logo {
            display: flex;
            align-items: center;
            gap: 15px;
            font-size: 25px;
            font-weight: bold;
            color: #b76e79;
        }
        .logo img{
            width: 45px;
            height: 45px;
            object-fit: contain;
        }
        .logo span {
            display: flex;
            align-items: center;
        }
        nav a {
            color: #444;
            text-decoration: none;
            margin-left: 25px;
            font-size: 15px;
        }
        nav a:hover {
            color: #b76e79; 
        }
        .container {
            max-width: 850px;
            margin: 45px auto;
            padding: 0 20px;
        }
        h1 {
            text-align: center;
            color: #5a4145;
            margin-bottom: 10px;
        }
        .subtitle {
            text-align: center;
            color: #888;
            margin-bottom: 30px;
        }
        .card {
            background: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.08);
            margin-bottom: 25px;
        }
        label {
            display: block;
            margin: 17px 0 8px;
            font-weight: bold;
            color:#5a4145
        }
        input {
            width: 100%;
            padding: 13px;
            border: 1px solid #ddd;
            border-radius: 7px;
            font-size: 15px;
        }
        .create-btn {
            width: 100%;
            margin-top: 25px;
            padding: 14px;
            border: none;
            border-radius: 8px;
            background: #c97894;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        .create-btn:hover {
            background: #b56782;
        }
        .message {
            text-align: center;
            margin-top: 20px;
            color: #888;
            font-size: 14px;
        }
        footer {
            text-align: center;
            padding: 25px;
            background-color: #5a4145;
            color: white;
            margin-top: 30px;
        }
    </style>
  </head>
  <body>
    <nav>
        <div class="logo"> <img src="S.png" alt="SalonSearcher Logo"><span>SalonSearcher</span></div>
        <div>
            <a href="index.html">Home</a>
            <a href="browse.html">Find a Provider</a>
            <a href="/profile">My Profile</a>
            <a href="/register">Create Account</a>
        </div>
    </nav>
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

            <form action="/register" method="POST" onsubmit="return validatePassword()">
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
    <footer>
        <p>© 2026 SalonSearcher</p>
    </footer>
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
  </body>
</html>