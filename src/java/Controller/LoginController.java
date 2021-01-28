/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lahiru De silva
 */
public class LoginController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        //Create user object from user entered data (taken from request)
        User user = new User(request.getParameter("email"), request.getParameter("password"));
        DAO dao = new DAO();
        
        String accountType = "";
        try {
            
            //Authenticate user by using dao object.
            accountType = dao.authenticateUser(user);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("email", user.getEmail());
        
        switch (accountType) {
            
            //Assign attributes to session data and redirect to facade that handles user direction
            
            case "admin":
                session.setAttribute("userType", "admin");
                response.sendRedirect("AdminHomeController");
                break;
            case "member":
                session.setAttribute("userType", "member");
                response.sendRedirect("UserHomeController");
                break;
            default:
                //If user doesn't have an account
                out.println("<p style= color:white>email or password doesn't exits </p>");//display error message to user
                request.getRequestDispatcher("Login.jsp").include(request, response);
                
        }
        
    }

   

}
