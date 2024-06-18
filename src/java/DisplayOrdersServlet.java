import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/displayOrders")
public class DisplayOrdersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            String query = "SELECT * FROM orders WHERE email=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            while (rs.next()) {
                out.println("<p>Medicine: " + rs.getString("medicineName") + "</p>");
                out.println("<p>Quantity: " + rs.getInt("quantity") + "</p>");
                out.println("<hr>");
            }
            out.println("</body></html>");

            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
