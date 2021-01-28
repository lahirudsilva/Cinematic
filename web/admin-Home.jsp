<%-- 
    Document   : admin-Home
    Created on : Jan 25, 2021, 12:19:29 AM
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
        <c:url var="movie" value="MovieController">
            <c:param name="command" value="MovieLists"></c:param>
        </c:url> 
        <c:url var="Ended" value="MovieController">
            <c:param name="command" value="EndedMovieLists"></c:param>
        </c:url> 


        <header class="top-navbar fixed-top-navbar">
            <div class="grid-nav-top">
                <div class="logo">
                    <h1 align="center" style="margin-left: 40px; margin-top: 5px;" >Cinematic</h1>

                </div>

                <div class="logout" >
                    <a href="${logout}"Style="color: black; padding: auto"><b>Logout</b></a>  </div>

                <!--  <div class="box-search">
                      <input type="text" placeholder="Search..." required>
                      <button type="submit"><i class="fa fa-search"></i></button>
                  </div>-->

            </div>
        </header>
        <br>
        <br>

        <!-- SIDEBAR DESKTOP -->

        <div class="wrapper-left">
            <div class="sidebar-left">
                <br />
                <div class="grid-sidebar bg-active " style="margin-top: 12px;">

                    <div >
                        <p align="center" style="margin-top: 4px; color: white;" class="">Booking Records</p>
                    </div>
                    <br>

                </div>

                <div class="grid-sidebar">
                    <div >
                        <a href="${movie}"><p align="center"  style="margin-top: 4px; color: gray">On Screen & Up coming movies</p></a>
                    </div>
                </div>

                <div class="grid-sidebar">
                    <div >
                        <a href="${Ended}"><p align="center"  style="margin-top: 4px; color: gray">Movie Archive </p></a>
                    </div>
                </div>

                <div class="grid-sidebar">
                    <div >
                        <a href="add-movies.jsp"><p align="center"  style="margin-top: 4px; color: gray">Add Movies </p></a>
                    </div>
                </div>



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


                        <div class="search">
                            <form method="GET" action="AdminHomeController">
                                <input type="hidden" name="command" value="SORTDATE">
                                <label Style="color: white;margin-top: 20px;">Sort Screening by date: </label>
                                <select name ="DateSearch" class="search_categories">
                                    <option value="" disabled selected >Select a Sorting algorithm</option>
                                    <option name="Ascending">Ascending</option>
                                    <option name="Descending">Descending</option>
                                </select>

                                <i class="fa fa-filter" aria-hidden="true"></i>
                                <div class="filter">
                                    <input type="submit" value="Filter" class="btn">
                                </div>

                            </form>
                        </div>
                    </div>

                    <table class="datatable"> 
                        <thead>
                            <tr>
                                <th>Booking ID</th>
                                <th>Reservation Date</th>
                                <th>user ID</th>
                                <th>Movie Id</th>
                                <th>Theater Name</th>
                                <th>Screening Date</th>
                                <th>Screening Time</th>
                                <th>Ticket Amount</th>
                                <th>Total Price</th>
                            </tr>
                        </thead>
                        <c:forEach var="BookingDetails" items="${BookingDetails}">
                            <tbody>
                                <tr>
                                    <td>${BookingDetails.bookId}</td>
                                    <td>${BookingDetails.reservationDate}</td>
                                    <td>${BookingDetails.userId}</td>
                                    <td>${BookingDetails.movieId}</td>
                                    <td>${BookingDetails.theaterName}</td>
                                    <td>${BookingDetails.date}</td>
                                    <td>${BookingDetails.time}</td>
                                    <td>${BookingDetails.noOfTicket}</td>
                                    <td>${BookingDetails.totalPrice}</td>

                                </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </div>
            </center>
        </div>
    </body>
</html>
