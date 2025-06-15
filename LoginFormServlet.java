package org.example.studentform;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class LoginFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String myname = req.getParameter("name");
        String myrollnum = req.getParameter("rnum");
        String myage = req.getParameter("age");
        String myemail = req.getParameter("email");
        String mybranch = req.getParameter("branch");

        PrintWriter out = res.getWriter();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindemo", "root", "chandu");
            PreparedStatement ps = conn.prepareStatement("insert into register (name, rnum, age, email, branch) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, myname);
            ps.setInt(2, Integer.parseInt(myrollnum));
            ps.setInt(3, Integer.parseInt(myage));
            ps.setString(4, myemail);
            ps.setString(5, mybranch);

            int rows = ps.executeUpdate();
            if(rows > 0) {
                res.setContentType("text/html");
                out.print("<h3 style = 'color : green' > User Registered Successfully");
                RequestDispatcher rd = req.getRequestDispatcher("forms.jsp");
                rd.include(req, res);
            }
            else {
                res.setContentType("text/html");
                out.print("<h3 style = 'color : red' > User not  Registered  due to error");
                RequestDispatcher rd = req.getRequestDispatcher("forms.jsp");
                rd.include(req, res);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            res.setContentType("text/html");
            out.print("<h3 style = 'color : red' >  Exception Occured : " + e.getMessage() + "</h3");
            RequestDispatcher rd = req.getRequestDispatcher("forms.jsp");
            rd.include(req, res);
        }

    }
}
