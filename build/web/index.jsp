<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pharmacy Management System</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <style>
    body {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      background-color: black
    }

    .container {
      background-color: #f8f9fa; 
      padding: 20px;
      border-radius: 10px; /* Rounded corners */
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Box shadow */
    }
  </style>
</head>
<body>
  <div class="container">
    <h1 class="text-center">Pharmacy Management System</h1>
    <div class="text-center">
      <button type="button" class="btn btn-primary" id="loginButton">Login</button>
      <button type="button" class="btn btn-warning" id="signUpButton">Sign Up</button>
    </div>
  </div>

  <script>
    document.getElementById("loginButton").addEventListener("click", function() {
      window.location.href = "Login.jsp";
    });

    // If you also have a sign-up page, you can add a similar event listener for the Sign Up button
    document.getElementById("signUpButton").addEventListener("click", function() {
      window.location.href = "Signup.jsp"; // assuming you have a signup.html file
    });
  </script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
