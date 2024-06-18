import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/searchMedicine")
public class SearchMedicineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (!"seller".equals(role)) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String name = request.getParameter("name");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project";
            Connection con = DriverManager.getConnection(url, "root", "1234");

            String query = "SELECT * FROM medicines WHERE name=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            while (rs.next()) {
                out.println("<p>Name: " + rs.getString("name") + "</p>");
                out.println("<p>Category: " + rs.getString("category") + "</p>");
                out.println("<p>Price: " + rs.getDouble("price") + "</p>");
                out.println("<p>Quantity: " + rs.getInt("quantity") + "</p>");
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
