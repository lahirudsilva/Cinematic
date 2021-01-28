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
public class AdminDAO implements UserDAO {

    private static Connection connection = Database_Connection.getConnection();

    public AdminDAO() {

    }

    public boolean addMovies(Movies movie) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into movies (theaterName,movieName,category,imgUrl,ScreeningStatus) values (?, ?, ?, ?, ?)");
            statement.setString(1, movie.getTheaterName());
            statement.setString(2, movie.getMovieName());
            statement.setString(3, movie.getCategory());
            statement.setString(4, movie.getImageUrl());
            statement.setString(5, movie.getScreeningStatus());

            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("Error occured when adding movies " + e);
        }
        return false;
    }

    public List<Reservation> getAllBookings() throws SQLException {
        ArrayList<Reservation> allBookingList = new ArrayList();

        PreparedStatement ps = connection.prepareStatement("select * from reservations");
        ResultSet rs = ps.executeQuery();
        //executing the preparedStatement objetc

        while (rs.next()) {
            //while the ResultSet object has another value in the set, this while loop will get executed
            int bookId = rs.getInt("reservationId");
            int userId = rs.getInt("userId");
            int movieId = rs.getInt("movieId");
            String screeningDate = rs.getString("screeningDate");
            String screeningTime = rs.getString("screeningTime");
            String theaterName = rs.getString("theaterName");
            int ticketAmount = rs.getInt("NoofTickets");
            double TotalPrice = Double.parseDouble(rs.getString("TotalPrice"));
            String reservationDate = rs.getString("reservationDate");

            //getting all the values in the ResultSet object separately into String variables
            Reservation reservations = new Reservation(bookId, userId, movieId, screeningDate, screeningTime, theaterName, ticketAmount, TotalPrice, reservationDate);
            //instantiating object with the String varibales

            allBookingList.add(reservations);
            //adding the object to the ArrayList

        }
        return allBookingList;
    }

    public List<Movies> getAllOnScreenMovies() throws SQLException {
        ArrayList<Movies> allOnScreen = new ArrayList();

        PreparedStatement ps = connection.prepareStatement("select * from movies where ScreeningStatus= ? or ScreeningStatus= ?");
        ps.setString(1, "NowShowing");
        ps.setString(2, "ComingSoon");
        ResultSet rs = ps.executeQuery();
        //executing the preparedStatement objetc

        while (rs.next()) {
            //while the ResultSet object has another value in the set, this while loop will get executed
            int movieId = rs.getInt("movieId");
            String theaterName = rs.getString("theaterName");
            String movieName = rs.getString("movieName");
            String category = rs.getString("category");
            String imgUrl = rs.getString("imgUrl");
            String ScreeningStatus = rs.getString("ScreeningStatus");

            //getting all the values in the ResultSet object separately into String variables
            Movies movies = new Movies(movieId, theaterName, movieName, category, imgUrl, ScreeningStatus);
            //instantiating object with the String varibales

            allOnScreen.add(movies);
            //adding the object to the ArrayList

        }
        return allOnScreen;
    }

    public List<Movies> getAllScreenEndedMovies() throws SQLException {
        ArrayList<Movies> allOnScreen = new ArrayList();

        PreparedStatement ps = connection.prepareStatement("select * from movies where ScreeningStatus=?");
        ps.setString(1, "ScreeningEnded");
        ResultSet rs = ps.executeQuery();
        //executing the preparedStatement objetc

        while (rs.next()) {
            //while the ResultSet object has another value in the set, this while loop will get executed
            int movieId = rs.getInt("movieId");
            String theaterName = rs.getString("theaterName");
            String movieName = rs.getString("movieName");
            String category = rs.getString("category");
            String imgUrl = rs.getString("imgUrl");
            String ScreeningStatus = rs.getString("ScreeningStatus");

            //getting all the values in the ResultSet object separately into String variables
            Movies movies = new Movies(movieId, theaterName, movieName, category, imgUrl, ScreeningStatus);
            //instantiating object with the String varibales

            allOnScreen.add(movies);
            //adding the object to the ArrayList

        }
        return allOnScreen;
    }

//    public boolean updateMovieStatus(Movies movieObject) throws SQLException {
//
//        

    public boolean updateMovieStatus(String status, int movieID) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update movies set ScreeningStatus = ? where movieId = ? ");

        ps.setString(1, status);
        ps.setInt(2, movieID);
        ps.executeUpdate();
        
        
        
        
        return true;

    }

}
