import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (!"buyer".equals(role)) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String email = (String) session.getAttribute("name");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project";
            Connection con = DriverManager.getConnection(url, "root", "1234");

            String query = "INSERT INTO orders (email, medicineName, quantity) SELECT email, medicineName, quantity FROM cart WHERE email=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                String clearCartQuery = "DELETE FROM cart WHERE email=?";
                PreparedStatement clearCartStmt = con.prepareStatement(clearCartQuery);
                clearCartStmt.setString(1, email);
                clearCartStmt.executeUpdate();

                response.sendRedirect("buyer_homepage.jsp");
            } else {
                response.getWriter().println("Failed to place order.");
            }

            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
