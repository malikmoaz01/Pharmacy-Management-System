<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Display Medicines</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Available Medicines</h2>
        <a href="buyer_homepage.jsp" class="btn btn-link">Back to Homepage</a>
        <div id="medicines">
            <!-- Medicines will be displayed here -->
        </div>
    </div>
    <script>
        window.onload = function() {
            fetch('displayMedicines')
                .then(response => response.text())
                .then(data => {
                    document.getElementById('medicines').innerHTML = data;
                });
        }
    </script>
</body>
</html>
