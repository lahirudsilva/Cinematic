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
import static java.lang.System.out;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lahiru De silva
 */
public class RegisterController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            registerUser(request, response);
        }catch (SQLException e){
            System.out.println(e);
        }
        
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        DAO dao = new DAO();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
         // Get data from request parameters
         String email = request.getParameter("email");
         String firstName = request.getParameter("fName");
         String lastName = request.getParameter("lName");
         String password = request.getParameter("password");
         String dob = request.getParameter("dob");
         String phoneNo = request.getParameter("phNumber");
         String role = "member";
         
         if(dao.checkUsers(email)){
               out.print("<p style= color:white>user already exits try again..</p>");
               request.getRequestDispatcher("Register.jsp").include(request, response);

           }
           else{
               User theuser = new User(firstName,lastName,email,password,dob,phoneNo,role);
               
               dao.Register(theuser);
               
               out.print("<p style= color:white>Successfully Registered..</p>");
               request.getRequestDispatcher("Login.jsp").include(request, response);

           }
    }

}
