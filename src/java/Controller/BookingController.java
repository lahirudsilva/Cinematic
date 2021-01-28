/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BookAdapter;
import Model.BookAdapterInterface;
import Model.DAOFactory;
import Model.MailClass;
import Model.MemberDAO;
import Model.MovieFactory;
import Model.Movies;
import Model.Reservation;
import Model.Ticket;
import Model.User;
import Model.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class BookingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //MOVIEINFO
        try {
            String command = request.getParameter("command");
            //getting the command parameter from the webpage into a String variable
            if (command == null) {
                command = "MOVIEINFO";
            }

            switch (command) {
                //switch-case statement to call methods depending on the command string variable
                case "MOVIEINFO":
                    getTicketDetails(request, response);
                    break;

                case "MYBOOKINGS":
                    myBookings(request, response);
                    break;
                default:
                    getTicketDetails(request, response);
                    break;

            }
        } catch (Exception e) {
            Logger.getLogger(AddMovieController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String command = request.getParameter("command");
            //getting the command parameter from the webpage into a String variable

            if (command == null) {
                command = "ADDBOOKING";
            }

            switch (command) {
                //switch-case statement to call methods depending on the command string variable

                case "ADDBOOKING":
                    AddBooking(request, response);
                    break;
                default:

            }
        } catch (Exception e) {
            Logger.getLogger(AddMovieController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void getTicketDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO daoObject = DAOFactory.createDAO("member");
        HttpSession theSession = request.getSession();
        
        

        int movieId = (Integer.parseInt(request.getParameter("movieId")));//getting the movieID from the web page
        theSession.setAttribute("MovieId", movieId);//setting movieID to the session

        Movies movieObject = ((MemberDAO) daoObject).getAllMovieInfo(movieId);
        request.setAttribute("BOOK", movieObject);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Booking.jsp");
        dispatcher.forward(request, response);

    }

    private void AddBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        UserDAO daoObject = DAOFactory.createDAO("member");
        HttpSession theSession = request.getSession();

        String email = (String) theSession.getAttribute("email");
        String movieName = request.getParameter("movieName");
        int movieId = (Integer.parseInt(request.getParameter("movieId")));
        String screeningDate = request.getParameter("bookDate");
        String screeningTime = request.getParameter("bookTime");
        String payMethod = request.getParameter("payMethod");
        int adultTicket = (Integer.parseInt(request.getParameter("Adult")));
        int childTicket = (Integer.parseInt(request.getParameter("Child")));
        String theaterName = request.getParameter("theaterName");
        
         theSession.setAttribute("movieName", movieName);
         theSession.setAttribute("screeningDate", screeningDate);
        theSession.setAttribute("screeningTime", screeningTime);
        theSession.setAttribute("theaterName", theaterName);
        
         Movies movieObject = MovieFactory.getMovie(movieName);

        BookAdapterInterface bookObject = new BookAdapter();//adapter dp
        String BookedDate = bookObject.getDate();

        int ticketAmount = (adultTicket + childTicket);
        theSession.setAttribute("ticketSum", ticketAmount);//calclulating no of tickets\
        double total = 0;
        if (payMethod.equals("creditCard")) {
            total = (adultTicket * 1500 + childTicket * 850);//calculate total amount with discount for creadit card
        } else if (payMethod.equals("debitCard")) {
            total = (adultTicket * 1500 + childTicket * 850);//calculate total amount with discount for debit card
        }

        System.out.println(total);
        double totalAmount = total;
        //calculating the total price

        int useId = ((MemberDAO) daoObject).getUserId(email);

        Reservation reservation = new Reservation(useId,movieId, movieObject, screeningDate, screeningTime, theaterName, payMethod, ticketAmount, totalAmount, BookedDate);

        boolean isBookingAdded = ((MemberDAO) daoObject).addBookings(reservation);
        Reservation madeBooking = ((MemberDAO) daoObject).getBooking();
        theSession.setAttribute("Bookinfo", madeBooking);

        if (isBookingAdded) {

            paymentConfirm(request, response);

        } else {
            out.println("<center><h2>Failed to Book tickets</h2></center>"); //else, display the error message
            request.getRequestDispatcher("/booking.jsp").include(request, response);

        }


    }

    private void paymentConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        UserDAO daoObject = DAOFactory.createDAO("member");
        MailClass mail = MailClass.getMailInstance(); 

        HttpSession theSession = request.getSession();

        String email = (String) theSession.getAttribute("email");
        String movieName = (String) theSession.getAttribute("movieName");
        String screeningDate = (String) theSession.getAttribute("screeningDate");
        String screeningTime = (String) theSession.getAttribute("screeningTime");
        String theaterName = (String) theSession.getAttribute("theaterName");
        
        

        Reservation resObject = (Reservation) theSession.getAttribute("Bookinfo");
        request.setAttribute("ticket", resObject);

        //adding tickets
        int ticketAmout = (int) theSession.getAttribute("ticketSum");

        int reserveID = ((MemberDAO) daoObject).getBookId();

        Ticket ticket = new Ticket(reserveID);
        
        mail.sendMail("Reservation ID: "+reserveID+"Your reservation was made successfully ",  "ONINE-RECIPT:\n\n \n"+"Movie Name: "+movieName +"\n"+"Screening Date: "+screeningDate+"\n"+"Screening Time: "+screeningTime+"\n"+"Thearter: "+theaterName+"\n"+"Your Ticket: "+ticketAmout + "\n\nBest Regards,\nTeam Cinematic", email);

        boolean isAdded = ((MemberDAO) daoObject).addTickets(ticketAmout, ticket);

        if (isAdded) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/payments.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher rs = request.getRequestDispatcher("/User-Reservations.jsp");
            rs.forward(request, response);
        }

    }

    private void myBookings(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        UserDAO daoObject = DAOFactory.createDAO("member");

        HttpSession theSession = request.getSession();
        String email = (String) theSession.getAttribute("email");

        int userId = ((MemberDAO) daoObject).getUserId(email);
        List<Reservation> myBookingList = ((MemberDAO) daoObject).getMyBookings(userId);

        if (myBookingList.isEmpty()) {
            //check if list is empty  
            ;//display error message to user
            request.getRequestDispatcher("/User-Reservations.jsp").include(request, response);
            out.println("<h2 style= color:white><center>No Booking done yet..</center> </h2>");
        } else {
            request.setAttribute("UserReservations", myBookingList);
            RequestDispatcher rs = request.getRequestDispatcher("/User-Reservations.jsp");
            rs.forward(request, response);
        }
    }

}
