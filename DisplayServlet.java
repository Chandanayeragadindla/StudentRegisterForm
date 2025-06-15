package org.example.studentform;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisplayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        out.println("<html><head><title>Student List</title>");
        out.println("<style>");
        out.println("body { font-family: 'Segoe UI', sans-serif; background-color: #f0f2f5; padding: 30px; }");
        out.println("h2 { text-align: center; color: #1a237e; margin-bottom: 30px; }");
        out.println("table { width: 90%; margin: 0 auto; border-collapse: collapse; background: white; box-shadow: 0 10px 25px rgba(0,0,0,0.1); }");
        out.println("th, td { padding: 12px 15px; border: 1px solid #ddd; text-align: center; }");
        out.println("th { background-color: #1a73e8; color: white; }");
        out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
        out.println("tr:hover { background-color: #e3f2fd; }");
        out.println(".btn-back { display: block; margin: 30px auto; padding: 12px 25px; background-color: #4CAF50; color: white; font-weight: bold; border: none; border-radius: 6px; text-decoration: none; transition: 0.3s; text-align: center; }");
        out.println(".btn-back:hover { background-color: #388e3c; }");
        out.println("</style>");
        out.println("</head><body>");

        out.println("<h2> Student List</h2>");
        out.println("<table>");
        out.println("<tr><th>Name</th><th>Roll Number</th><th>Age</th><th>Email</th><th>Branch</th></tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindemo", "root", "chandu");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM register");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getInt("rnum") + "</td>");
                out.println("<td>" + rs.getInt("age") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("branch") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");


            out.println("<a href='forms.jsp' class='btn-back'>⬅ Back to Home</a>");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='color:red; text-align:center;'>Error: " + e.getMessage() + "</h3>");
        }

        out.println("</body></html>");
    }
}
