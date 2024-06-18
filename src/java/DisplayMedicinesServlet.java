import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/displayMedicines")
public class DisplayMedicinesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (!"buyer".equals(role)) {
            response.sendRedirect("Login.jsp");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project";
            Connection con = DriverManager.getConnection(url, "root", "1234");

            String query = "SELECT * FROM medicines";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Available Medicines</h1>");
            while (rs.next()) {
                out.println("<p>Name: " + rs.getString("name") + "</p>");
                out.println("<p>Category: " + rs.getString("category") + "</p>");
                out.println("<p>Price: " + rs.getDouble("price") + "</p>");
                out.println("<p>Quantity: " + rs.getInt("quantity") + "</p>");
                out.println("<hr>");
            }
            out.println("</body></html>");

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
