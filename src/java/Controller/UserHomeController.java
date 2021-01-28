/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAOFactory;
import Model.MemberDAO;
import Model.Movies;
import Model.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lahiru De silva
 */
// Facade pattern to direct users to respective home page according to user type
public class UserHomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String command = request.getParameter("command");
        //getting the command parameter from the webpage into a String variable
        try {
            if (command == null) {
                command = "HOME";
            }

            switch (command) {
                //switch-case statement to call methods depending on the command string variable

                case "HOME":
                    sendUserHome(request, response);
                    break;
                case "HomePage":
                    HomePage(request, response);
                    break;
                default:
                    sendUserHome(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            sendUserHome(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendUserHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//         RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
//        dispatcher.forward(request, response);
        HomePage(request, response);
    }

    private void HomePage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        UserDAO daoObject = DAOFactory.createDAO("member");
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("email");

        List<Movies> MovieList = ((MemberDAO) daoObject).getAllscreeningMovies();

        if (MovieList.isEmpty()) {
            //check if list is empty 

            request.getRequestDispatcher("Home.jsp").include(request, response);
            out.println("<h2 style= color:white><center>No Movie available</center> </h2>");//display error message to user

        } else {
            request.setAttribute("MOVIE_info", MovieList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
            dispatcher.forward(request, response);
        }

    }

}
