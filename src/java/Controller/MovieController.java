/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AdminDAO;
import Model.DAOFactory;
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

/**
 *
 * @author Lahiru De silva
 */
public class MovieController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String command = request.getParameter("command");
            //getting the command parameter from the webpage into a String variable
            if (command == null) {
                command = "MovieLists";
            }
            switch (command) {
                //switch-case statement to call methods depending on the command string variable

                case "MovieLists":
                    getAllmovies(request, response);
                    break;
                case "EndedMovieLists":
                    getAllEndedmovies(request, response);
                    break;
                case "ChangeStatus":
                    changeMovieStatus(request, response);
                    break;

                default:
                    getAllmovies(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void getAllmovies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        UserDAO daoObject = DAOFactory.createDAO("admin");

        List<Movies> OnScreen = ((AdminDAO) daoObject).getAllOnScreenMovies();

        if (OnScreen.isEmpty()) {
            request.getRequestDispatcher("Manage-Movies.jsp").include(request, response);
            out.println("<h2 style= color:white><center>No OnScreen movies available</center> </h2>");//display error message to user

        } else {
            request.setAttribute("OnScreen", OnScreen);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Manage-Movies.jsp");
            dispatcher.forward(request, response);

        }

    }

    private void changeMovieStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        UserDAO daoObject = DAOFactory.createDAO("admin");

        String status = request.getParameter("status");
        int movieID = (Integer.parseInt(request.getParameter("movieId")));
        System.out.println(movieID);

        boolean isDone = ((AdminDAO) daoObject).updateMovieStatus(status, movieID);
        response.sendRedirect("MovieController");
    }

    private void getAllEndedmovies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        UserDAO daoObject = DAOFactory.createDAO("admin");

        List<Movies> ScreenEnd = ((AdminDAO) daoObject).getAllScreenEndedMovies();
        request.setAttribute("ScreenEnd", ScreenEnd);

        if (ScreenEnd.isEmpty()) {
            request.getRequestDispatcher("Archived-Movies.jsp").include(request, response);
            out.println("<h2 style= color:white><center>No Archived movies </center> </h2>");//display error message to user

        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Archived-Movies.jsp");
            dispatcher.forward(request, response);
        }

    }

}
