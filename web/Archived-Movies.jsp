<%-- 
    Document   : Archived-Movies
    Created on : Jan 26, 2021, 10:58:20 AM
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

                <div class="grid-sidebar ">
                    <div >
                        <a href="${movie}"><p align="center" href="${myBooking}" style="margin-top: 4px; color: gray; ">On Screen & Up coming movies</p></a>
                    </div>
                </div>

                <div class="grid-sidebar bg-active">
                    <div >
                        <a href="${Ended}"><p align="center"  style="margin-top: 4px; color: white; padding: 7px;" >Movie Archive </p></a>
                    </div>
                </div>

                <div class="grid-sidebar">
                    <div >
                        <a href="add-movies.jsp"><p align="center"  style="margin-top: 4px; color: gray">Add Movies  </p></a>
                    </div>
                </div>




            </div>


        </div>


        <h2 style="text-align: center;margin-left: -650px;margin-top: 90px;">Screening-Ended Movies</h2>

        <div class = "container" style="margin-left: 300px;margin-top: 27px;  margin-right: auto;">

            <center>
                <div class="datatable-container">
                    <!-- ======= Header tools ======= -->
                    <div class="header-tools">

                    </div>
                    <div style="height:700px;overflow:auto; ">
                        <table class="datatable"> 
                            <thead>
                                <tr>
                                    <th>Movie ID</th>
                                    <th>Movie Name</th>
                                    <th>Category</th>
                                    <th>Theater Name</th>
                                    <th>image</th>
                                    <th>Screening Status</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <c:forEach var="MovieDetails" items="${ScreenEnd}">
                                <tbody>
                                    <tr>
                                        <td>${MovieDetails.movieId}</td>
                                        <td>${MovieDetails.movieName}</td>
                                        <td>${MovieDetails.category}</td>
                                        <td>${MovieDetails.theaterName}</td>
                                        <td><img src="${MovieDetails.imageUrl}" width="90px"></td>

                                        <c:choose>
                                            <c:when test="${MovieDetails.screeningStatus =='ScreeningEnded'}">
                                                <td>
                                                    <form action="MovieController" method="GET">
                                                        <input type="hidden" name="command" value="ChangeStatus">
                                                        <input type="hidden" name="movieId" value="${MovieDetails.movieId}">

                                                        <select name="status"class="status">
                                                            <option value="" disabled selected style="color:gray">${MovieDetails.screeningStatus}</option>
                                                            <option value="NowShowing">NowShowing</option>

                                                        </select>
                                                        <div class="subbutton" style="margin-left: 20px;">

                                                            <input type="submit" value="Change status" align="center">
                                                        </div>
                                                    </form>


                                                </td>
                                            </c:when>
                                        </c:choose>
                                        <td><a id="delete" onclick=" deleted()" href="${deleteLink}">Delete</a></td>




                                    </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </center>

        </div>
    </body>
</html>
