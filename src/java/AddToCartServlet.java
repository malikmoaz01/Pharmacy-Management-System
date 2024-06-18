import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (!"buyer".equals(role)) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String medicineName = request.getParameter("medicineName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String email = (String) session.getAttribute("name");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project";
            Connection con = DriverManager.getConnection(url, "root", "1234");

            String query = "INSERT INTO cart (email, medicineName, quantity) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, medicineName);
            pstmt.setInt(3, quantity);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                response.sendRedirect("buyer_homepage.jsp");
            } else {
                response.getWriter().println("Failed to add to cart.");
            }

            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
