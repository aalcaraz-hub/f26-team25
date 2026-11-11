<#macro page title bodyClass="">
<!doctype html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SalonSearcher - ${title}</title>
    <link rel="stylesheet" href="/styles.css">
  </head>

  <body class="${bodyClass}">
    <nav>
        <div class="logo"> <img src="/images/S.png" alt="SalonSearcher Logo"><span>SalonSearcher</span></div>
        <div>
            <a href="/">Home</a>
            <a href="/browse">Find a Provider</a>
            <a href="/profile">My Profile</a>
            <a href="/customers">Create Account</a>
        </div>
    </nav>
    <#nested>
    <footer>
        &copy; 2026 SalonSearcher. All rights reserved.
    </footer>
  </body>
</html>
</#macro>