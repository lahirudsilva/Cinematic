<%-- 
    Document   : Manage-Movies
    Created on : Jan 25, 2021, 5:14:02 PM
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
        <c:url var="Ended" value="MovieController">
            <c:param name="command" value="EndedMovieLists"></c:param>
        </c:url> 
        <c:url var="home" value="AdminHomeController">
            <c:param name="command" value="HOME"></c:param>
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
                <div class="grid-sidebar " style="margin-top: 12px;">

                    <div >
                        <a href="${home}"><p align="center" style="margin-top: 4px; color: gray;" class="">Booking Records</p></a>
                    </div>
                    <br>

                </div>

                <div class="grid-sidebar bg-active">
                    <div >
                        <p align="center" href="${myBooking}" style="margin-top: 4px; color: white; padding: 7px;">On Screen & Up coming movies</p>
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
        <h2 style="text-align: center;margin-left: -650px;margin-top: 90px;">Upcoming & Onscreen Movies</h2>

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



                    </div>
                    <div style="height:700px;overflow:auto;  ">
                        <table class="datatable"> 
                            <thead>
                                <tr>
                                    <th>Movie ID</th>
                                    <th>Movie Name</th>
                                    <th>Category</th>
                                    <th>Theater Name</th>
                                    <th>Movie Cover</th>
                                    <th>Screening Status</th>
                                    <th></th>
                                </tr>
                            </thead>



                            <tbody>
                                <c:forEach var="MovieDetails" items="${OnScreen}">
                                    <tr>
                                        <td>${MovieDetails.movieId}</td>
                                        <td>${MovieDetails.movieName}</td>
                                        <td>${MovieDetails.category}</td>
                                        <td>${MovieDetails.theaterName}</td>
                                        <td><img src="${MovieDetails.imageUrl}" width="90px"></td>

                                        <c:choose>
                                            <c:when test="${MovieDetails.screeningStatus =='NowShowing'}">
                                                <td>
                                                    <form action="MovieController" method="GET">
                                                        <input type="hidden" name="command" value="ChangeStatus">
                                                        <input type="hidden" name="movieId" value="${MovieDetails.movieId}">

                                                        <select name="status"class="status">
                                                            <option value="" disabled selected style="color:gray">${MovieDetails.screeningStatus}</option>
                                                            <option value="ScreeningEnded">ScreeningEnded</option>

                                                        </select>

                                                        <input class="submit" type="submit" value="Change status" align="center">
                                                    </form>


                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>  
                                                    <c:when test="${MovieDetails.screeningStatus =='ComingSoon'}">
                                                        <td>

                                                            <form action="MovieController" method="GET">
                                                                <input type="hidden" name="command" value="ChangeStatus">
                                                                <input type="hidden" name="movieId" value="${MovieDetails.movieId}">
                                                                <select name="status" class="status">
                                                                    <option value="" disabled selected style="color:gray">${MovieDetails.screeningStatus}</option>
                                                                    <option value="NowShowing">NowShowing</option>
                                                                </select>
                                                                <div class="subbutton" >

                                                                    <input type="submit" value="Change status" >
                                                                </div>
                                                            </form>


                                                        </td>
                                                    </c:when>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>

                                    </tr>
                                </c:forEach>
                            </tbody>

                        </table>
                    </div>
                </div>
            </center>
        </div>



    </body>

</html>
