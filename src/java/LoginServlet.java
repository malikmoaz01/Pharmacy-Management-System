import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.Cookie;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project";
            Connection con = DriverManager.getConnection(url, "root", "1234");

            String query = "SELECT * FROM register2 WHERE role=? AND email=? AND password=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, role);
            pstmt.setString(2, email);
            pstmt.setString(3, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // If credentials are valid, create a session and store user type
                HttpSession session = request.getSession();
                session.setAttribute("name", email);
                session.setAttribute("role", role);

                Cookie userCookie = new Cookie("user", email);
                userCookie.setMaxAge(60 * 60 * 24); // Set cookie to expire in 1 day
                response.addCookie(userCookie);

                // Forward to appropriate homepage based on user type
                RequestDispatcher dispatcher;
                if ("seller".equals(role)) {
                    dispatcher = request.getRequestDispatcher("seller_homepage.jsp");
                } else {
                    dispatcher = request.getRequestDispatcher("buyer_homepage.jsp");
                }
                dispatcher.forward(request, response);
            } else {
                // If credentials are not valid, display an error message
                out.println("<html><head><title>Login Failed</title></head><body>");
                out.println("<h1>Login Failed</h1>");
                out.println("<p>Invalid role, email, or password. Please try again.</p>");
                out.println("</body></html>");
            }

            // Close resources
            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h1>Error</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
        } finally {
            out.close();
        }
    }
}
