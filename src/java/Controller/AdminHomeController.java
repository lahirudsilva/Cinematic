/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AdminDAO;
import Model.AscendingSort;
import Model.DAOFactory;
import Model.DescendingSort;
import Model.Reservation;
import Model.StrategySorter;
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
public class AdminHomeController extends HttpServlet {

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
                    AdminHome(request, response);
                    break;
                case "SORTDATE":
                    sortDate(request, response);
                default:
                    AdminHome(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AdminHome(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void AdminHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        HomePage(request, response);
    }

    private void HomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        UserDAO daoObject = DAOFactory.createDAO("admin");
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("email");

        List<Reservation> BookList = ((AdminDAO) daoObject).getAllBookings();
        request.setAttribute("BookingDetails", BookList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-Home.jsp");
        dispatcher.forward(request, response);
    }

    private void sortDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortType = request.getParameter("DateSearch");
        
        if (sortType.equals("Ascending")) {
            StrategySorter sSorter = new StrategySorter();

            sSorter.setSortObject(new AscendingSort());

            List<Reservation> bookingList = sSorter.sortScreeningDate();

            request.setAttribute("BookingDetails", bookingList);
            request.getRequestDispatcher("/admin-Home.jsp").forward(request, response);

        } else {

            StrategySorter sSorter = new StrategySorter();

            sSorter.setSortObject(new DescendingSort());

             List<Reservation> bookingList = sSorter.sortScreeningDate();

            request.setAttribute("BookingDetails", bookingList);
            request.getRequestDispatcher("/admin-Home.jsp").forward(request, response);
        }
    }

}
