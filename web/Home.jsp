<%-- 
    Document   : Home
    Created on : Jan 10, 2021, 2:36:01 AM
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
        <link rel="stylesheet" href="CSS/Movie-cards.css">
        <link rel="icon" type="image/png" href="images/cinematicLogo.png">
        <title>Cinematic - Buy Movie Tickets Online for the Latest Movies</title>
    </head>
    <body>

        <c:url var="logout" value="LogoutController">

        </c:url>  



        <!-- NAVBAR TOP DESKTOP -->
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
        <c:url var="myBooking" value="BookingController">
            <c:param name="command" value="MYBOOKINGS"/>
        </c:url>
        <c:url var="home" value="UserHomeController" >
            <c:param name="command" value="HomePage"/>
        </c:url>

        <div class="wrapper-left">
            <div class="sidebar-left">
                <br />
                <div class="grid-sidebar bg-active " style="margin-top: 12px;">

                    <div >
                        <a href="${home}"><p align="center" style="margin-top: 4px; color: white;" class="">Now Showing</p></a>
                    </div>
                    <br>

                </div>

                <div class="grid-sidebar">
                    <div >
                        <a  href="${myBooking}" ><p align="center" style="margin-top: 4px; color: gray; align-self: center;">My Bookings</p></a>
                    </div>
                </div>

            </div>

        </div>


        <!-- CARDS -->
        <div class = "container" style="margin-left: 350px; margin-right: auto;">
            <div class="uname" >
                <%
                    String name = (String) session.getAttribute("email");
                    out.print("Hello, " + name);
                %>
            </div>
            <c:forEach var="movie" items="${MOVIE_info}">
                <c:url var="book" value ="BookingController">
                    <c:param name="command" value="MOVIEINFO"/>
                    <c:param name="movieId" value="${movie.movieId}"/>
                    <c:param name="movieName" value="${movie.movieName}"/>
                    <c:param name="theaterName" value="${movie.theaterName}"/>
                </c:url>
                <div class="movie-card">
                    <div class="movie-header image">
                        <div class="header-icon-container">

                            <img src="${movie.imageUrl}" >

                        </div>
                    </div> <!--movie-header-->
                    <div class="movie-content">
                        <div class="movie-content-header">
                            <a href="#">
                                <h3 class="movie-title">${movie.movieName}</h3>
                            </a>
                            <div style="float: right;"></div>
                        </div>
                        <div class="movie-info">
                            <div class="info-section">
                                <label>Theater</label>
                                <span>${movie.theaterName}</span>
                            </div><!--date,time-->

                            <div class="info-section">
                                <a class="bookBtn" href="${book}">Book now</a>
                            </div><!--seat-->
                        </div>
                    </div><!--movie-content-->
                </div>
            </c:forEach><!--movie-card-->
        </div>




        <!-- NAVBAR FOOTER -->


    </div>
</body>
</html>
