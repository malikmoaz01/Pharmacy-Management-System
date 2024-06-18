<%@ page import="jakarta.servlet.http.HttpSession, jakarta.servlet.http.Cookie" %>
<%
    session = request.getSession();
    if (session.getAttribute("name") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    Cookie[] cookies = request.getCookies();
    String userCookieValue = "No user cookie found";

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("user".equals(cookie.getName())) {
                userCookieValue = cookie.getValue();
                break;
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Homepage</title>
</head>
<body>
    <h1>Welcome, Seller</h1>
    <p>Your session ID is: <%= session.getId() %></p>
    <p>Your user cookie is: <%= userCookieValue %></p>
</body>
</html>
