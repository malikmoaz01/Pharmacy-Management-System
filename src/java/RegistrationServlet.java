import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // Adding role parameter

        out.print("<html>");
        out.print("<head><title>LMS</title></head>");
        out.print("<body>");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project";
            Connection con = DriverManager.getConnection(url,"root","1234");

            String query = "INSERT INTO register2 (name, email, phone, password, role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, password);
            pstmt.setString(5, role);

            int rs = pstmt.executeUpdate();

            if(rs==1) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                dispatcher.forward(request, response);
            } else {
                out.print("<h1>Not Inserted</h1>");
            }

            // Close resources
            pstmt.close();
            con.close();

        } catch(Exception e) {
            out.println(e);
        }
        out.print("</body>");
        out.print("</html>");
    }
}
