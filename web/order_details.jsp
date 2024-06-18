<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Your Orders</h2>
        <div id="orders">
            <!-- Order details will be displayed here -->
        </div>
    </div>
    <script>
        window.onload = function() {
            fetch('displayOrders')
                .then(response => response.text())
                .then(data => {
                    document.getElementById('orders').innerHTML = data;
                });
        }
    </script>
</body>
</html>
