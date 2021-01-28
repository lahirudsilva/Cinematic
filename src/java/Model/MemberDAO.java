/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lahiru De silva
 */
public class MemberDAO implements UserDAO {

    private static Connection connection = Database_Connection.getConnection();

    public MemberDAO() {

    }

    public List<Movies> getAllscreeningMovies() throws SQLException {
        ArrayList<Movies> allMoviesList = new ArrayList();

        PreparedStatement ps = connection.prepareStatement("select * from movies where ScreeningStatus=?");
        ps.setString(1, "NowShowing");
        ResultSet rs = ps.executeQuery();
        //executing the preparedStatement objetc

        while (rs.next()) {
            //while the ResultSet object has another value in the set, this while loop will get executed
            int movieId = rs.getInt("movieId");
            String theaterName = rs.getString("theaterName");
            String movieName = rs.getString("movieName");
            String category = rs.getString("category");
            String imgUrl = rs.getString("imgUrl");

            //getting all the values in the ResultSet object separately into String variables
            Movies movies = new Movies(movieId, theaterName, movieName, category, imgUrl);
            //instantiating object with the String varibales

            allMoviesList.add(movies);
            //adding the object to the ArrayList

        }
        return allMoviesList;

    }

    public Movies getAllMovieInfo(int movieId) {
        Movies movie = null;
        //initializing an inquiry object

        try {
            PreparedStatement ps = connection.prepareStatement("select movieName,theaterName,category,imgUrl from movies where movieId=?");
            //creating an object of the PreparedStatement API and passing the sql SELECT query to it to retrieve the details needed
            ps.setInt(1, movieId);
            //setting the inquiryId to the preparedStatement variable
            ResultSet rs = ps.executeQuery();
            //executing the preparedStatement and catching the return in a ResultSet object

            while (rs.next()) {

                String theaterName = rs.getString("theaterName");
                String movieName = rs.getString("movieName");
                String category = rs.getString("category");
                String imgUrl = rs.getString("imgUrl");
                //getting the values from the ResultSet object to String variables

                movie = new Movies(movieId, theaterName, movieName, category, imgUrl);
                //instantiating a movie object with the String varibales

                return movie;

            }
        } catch (Exception e) {
            System.out.println(e);
            //printing error(if any)
        }
        return movie;

    }

    public int getUserId(String email) throws SQLException {

        int UserId = 0;

        PreparedStatement ps = connection.prepareStatement("select userId from user where email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            int userId = rs.getInt("userId");

            return userId;

        }

        return UserId;

    }

    public boolean addBookings(Reservation reservation) {
        
        
         try {
            PreparedStatement statement = connection.prepareStatement("insert into reservations (userId,movieId,movieName,screeningDate,screeningTime,theaterName,payMethod,NoofTickets,TotalPrice,reservationDate) values (?, ?, ?, ?, ?, ?, ?, ?,?, ?)");
            statement.setInt(1, reservation.getUserId());
            statement.setInt(2, reservation.getMovieId());
            statement.setString(3, reservation.getMovies().getMovieName());
            statement.setString(4, reservation.getDate());
            statement.setString(5, reservation.getTime());
            statement.setString(6, reservation.getTheaterName());
            statement.setString(7, reservation.getPayMethod());
            statement.setInt(8, reservation.getNoOfTicket());
            statement.setDouble(9, reservation.getTotalPrice());
            statement.setString(10, reservation.getReservationDate());

            statement.executeUpdate();
            

            return true;

        } catch (Exception e) {
            System.out.println("Error occured when adding movies " + e);
        }
        return false;
    }

    public Reservation getBooking() throws SQLException {
        Reservation reservation = null;
        int reservationId = getBookId();
        PreparedStatement theStatement = connection.prepareStatement("select * from reservations where reservationId=?");
        theStatement.setInt(1, reservationId);

        ResultSet theResult = theStatement.executeQuery();
        while (theResult.next()) {
            //access the content in the generated rows
            int BookiD = theResult.getInt("reservationId");
            double totalPrice = Double.parseDouble(theResult.getString("TotalPrice"));

            reservation = new Reservation(BookiD, totalPrice);
            return reservation;
        }
        return reservation;
    }

    public int getBookId() {
        try {
            PreparedStatement theStatement = connection.prepareStatement("select reservationId from reservations ORDER BY reservationId DESC Limit 1"); //get the last inserted order id
            ResultSet reservationIDQuery = theStatement.executeQuery();
            reservationIDQuery.next();
            int currentReservationIDQuery = reservationIDQuery.getInt("reservationId");

            return (currentReservationIDQuery);

        } catch (Exception ex) {
            System.out.println("Error fetching order id: " + ex);
        }
        return 0;
    }

    public List<Reservation> getMyBookings(int userId) throws SQLException {
        ArrayList<Reservation> MyBookingList = new ArrayList();

        PreparedStatement ps = connection.prepareStatement("select * from reservations where userId=?");
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        //executing the preparedStatement objetc

        while (rs.next()) {
            //while the ResultSet object has another value in the set, this while loop will get executed
            int bookId = rs.getInt("reservationId");
            String movieName = rs.getString("movieName");
            String screeningDate = rs.getString("screeningDate");
            String screeningTime = rs.getString("screeningTime");
            String theaterName = rs.getString("theaterName");
            int ticketAmount = rs.getInt("NoofTickets");
            double TotalPrice = Double.parseDouble(rs.getString("TotalPrice"));
            String reservationDate = rs.getString("reservationDate");
            
            Movies movieObject = MovieFactory.getMovie(movieName);

            Reservation reservations = new Reservation(bookId, movieObject, screeningDate, screeningTime, theaterName, ticketAmount, TotalPrice, reservationDate);
            //instantiating object with the String varibales

            MyBookingList.add(reservations);

        }
        return MyBookingList;

    }

    public boolean addTickets(int ticketAmout, Ticket ticket) {

        try {
            for (int x = 0; x < ticketAmout; x++) {
                PreparedStatement statement = connection.prepareStatement("insert into tickets (reservationId) values (?)");
                statement.setInt(1, ticket.getReservationId());

                statement.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error occured when adding movies " + e);
        }

        return false;
    }

    

}
