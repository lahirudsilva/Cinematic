<%-- 
    Document   : User-Reservations
    Created on : Jan 25, 2021, 11:55:06 AM
    Author     : Lahiru De silva 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Cherry+Swash" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="CSS/Home.css">
        <link rel="stylesheet" href="CSS/table.css">
        <link rel="icon" type="image/png" href="images/cinematicLogo.png">
        <title>Cinematic - Buy Movie Tickets Online for the Latest Movies</title>
    </head>
    <body>
        <c:url var="logout" value="LogoutController">

        </c:url> 
        <c:url var="myBooking" value="BookingController">
            <c:param name="command" value="MYBOOKINGS"/>
        </c:url>
        <c:url var="home" value="UserHomeController" >
            <c:param name="command" value="HomePage"/>
        </c:url>


        <header class="top-navbar fixed-top-navbar">
            <div class="grid-nav-top">
                <div class="logo">
                    <h1 align="center" style="margin-left: 40px; margin-top: 5px;" >Cinematic</h1>

                </div>


                <div class="logout" >
                    <a href="${logout}"Style="color: black; padding: auto"><b>Logout</b></a>  </div>

                 <div class="box-search">
                      <input type="text" placeholder="Search..." required>
                      <button type="submit"><i class="fa fa-search"></i></button>
                  </div>

            </div>
        </header>
        <br>
        <br>

        <!-- SIDEBAR DESKTOP -->


        <div class="wrapper-left">
            <div class="sidebar-left">
                <br />


                <div class="grid-sidebar">
                    <div >
                        <a href="${home}"><p align="center"  style="margin-top: 4px; color: gray">Now Showing</p></a>
                    </div>
                </div>
                <br>

                <div class="grid-sidebar bg-active">
                    <div >
                        <a href="${myBooking}"><p align="center"  style="margin-top: 4px; color: white; padding: 7px;">My Bookings</p></a>
                    </div>
                </div>
                <br>

            </div>
        </div>


        <div class = "container" style="margin-left: 300px;margin-top: 27px;  margin-right: auto;">
            <div class="uname" >
                <%
                    String name = (String) session.getAttribute("email");
                    out.print("Hello, " + name);
                %>
            </div>
            <center>
                <div class="datatable-container">
                    <!-- ======= Header tools ======= -->
                    <div class="header-tools">


                       <!-- <div class="search">
                            <form method="GET" action="AdminHomeController">
                                <input type="hidden" name="command" value="SORTDATE">
                                <label Style="color: white;margin-top: 20px;">Sort Screening date: </label>
                                <select name ="DateSearch" class="search_categories">
                                    <option value="" disabled selected >Select s Sorting algorithm</option>
                                    <option name="Ascending">Ascending</option>
                                    <option name="Descending">Descending</option>
                                </select>
                                <input type="submit" value="Search" class="btn">
                            </form>
                        </div> -->
                    </div>

                    <table class="datatable"> 
                        <thead>
                            <tr>
                                <th>Reservation ID</th>
                                <th>Movie Name</th>
                                <th>Theater Name</th>
                                <th>Screening Date</th>
                                <th>Screening Time</th>
                                <th>Ticket Amount</th>
                                <th>Total Price</th>
                                <th>Reservation Date</th>
                            </tr>
                        </thead>
                        <c:forEach var="UserReservations" items="${UserReservations}">
                            <tbody>
                                <tr>
                                    <td>${UserReservations.bookId}</td>
                                    <td>${UserReservations.movies.movieName}</td>
                                    <td>${UserReservations.theaterName}</td>
                                    <td>${UserReservations.date}</td>
                                    <td>${UserReservations.time}</td>
                                    <td>${UserReservations.noOfTicket}</td>
                                    <td>${UserReservations.totalPrice}</td>
                                    <td>${UserReservations.reservationDate}</td>

                                </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </div>
            </center>
        </div>
    </body>
</html>
