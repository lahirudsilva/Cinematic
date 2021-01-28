/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Lahiru De silva
 */
public class Reservation{
    int bookId , userId,movieId,noOfTicket;
    String date,time,payMethod,reservationDate,theaterName;
    Double totalPrice;
    Movies movies;
    
    

    public Reservation(int useId, int movieId, String screeningDate, String screeningTime,String theaterName, String payMethod, int ticketAmount, double totalAmount,String reservationDate) {
        this.userId = useId;
        this.movieId = movieId;
        this.date = screeningDate;
        this.time = screeningTime;
        this.theaterName =theaterName;
        this.payMethod = payMethod;
        this.noOfTicket = ticketAmount;
        this.totalPrice = totalAmount;
        this.reservationDate = reservationDate;
    }

    

    public Reservation(int bookId, double totalPrice) {
        this.bookId = bookId;
        this.totalPrice = totalPrice;
    }

    public Reservation(int bookId, int userId, int movieId, String screeningDate, String screeningTime,String theaterName, int ticketAmount, double TotalPrice,String reservationDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.movieId = movieId;
        this.date = screeningDate;
        this.time = screeningTime;
        this.theaterName = theaterName;
        this.noOfTicket = ticketAmount;
        this.totalPrice = TotalPrice;
        this.reservationDate = reservationDate;
    }

    public Reservation(int bookId, int movieId, String screeningDate, String screeningTime, String theaterName, int ticketAmount, double TotalPrice, String reservationDate) {
        this.bookId =bookId;
        this.movieId = movieId;
        this.date = screeningDate;
        this.time = screeningTime;
        this.theaterName = theaterName;
        this.noOfTicket = ticketAmount;
        this.totalPrice = TotalPrice;
        this.reservationDate = reservationDate;
        
    }
//
    public Reservation(int bookId, String movieName, String screeningDate, String screeningTime, String theaterName, int ticketAmount, double TotalPrice, String reservationDate) {
        this.bookId =bookId;
        this.movieId = movieId;
        this.date = screeningDate;
        this.time = screeningTime;
        this.theaterName = theaterName;
        this.noOfTicket = ticketAmount;
        this.totalPrice = TotalPrice;
        this.reservationDate = reservationDate;
    }
    

    public Reservation(int useId, int movieId, Movies movies, String screeningDate, String screeningTime, String theaterName, String payMethod, int ticketAmount, double totalAmount, String BookedDate) {
        this.userId = useId;
        this.movieId = movieId;
        this.movies = movies;
        this.date = screeningDate;
        this.time = screeningTime;
        this.theaterName =theaterName;
        this.payMethod = payMethod;
        this.noOfTicket = ticketAmount;
        this.totalPrice = totalAmount;
        this.reservationDate = BookedDate;
    }

    public Reservation(int bookId, Movies movies, String screeningDate, String screeningTime, String theaterName, int ticketAmount, double TotalPrice, String reservationDate) {
        this.bookId =bookId;
        this.movies = movies;
        this.date = screeningDate;
        this.time = screeningTime;
        this.theaterName = theaterName;
        this.noOfTicket = ticketAmount;
        this.totalPrice = TotalPrice;
        this.reservationDate = reservationDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getNoOfTicket() {
        return noOfTicket;
    }

    public void setNoOfTicket(int noOfTicket) {
        this.noOfTicket = noOfTicket;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    
    
    
    
    
               

    
    
    
    
    
}
