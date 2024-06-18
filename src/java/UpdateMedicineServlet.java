import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/updateMedicine")
public class UpdateMedicineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (!"seller".equals(role)) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String name = request.getParameter("name");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project";
            Connection con = DriverManager.getConnection(url, "root", "1234");

            String query = "UPDATE medicines SET category=?, price=?, quantity=? WHERE name=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, category);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, name);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                response.sendRedirect("seller_homepage.jsp");
            } else {
                response.getWriter().println("Failed to update medicine.");
            }

            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
