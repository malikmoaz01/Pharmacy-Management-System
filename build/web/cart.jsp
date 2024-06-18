<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Your Cart</h2>
        <div id="cart">
            <!-- Cart items will be displayed here -->
        </div>
    </div>
    <script>
        window.onload = function() {
            fetch('displayCart')
                .then(response => response.text())
                .then(data => {
                    document.getElementById('cart').innerHTML = data;
                });
        }
    </script>
</body>
</html>
