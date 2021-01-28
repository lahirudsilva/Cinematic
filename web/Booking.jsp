<%-- 
    Document   : Booking
    Created on : Jan 24, 2021, 12:59:36 AM
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
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <link rel="stylesheet" href="CSS/booking.css">
        <link rel="icon" type="image/png" href="images/cinematicLogo.png">
        <title>Cinematic - Buy Movie Tickets Online for the Latest Movies</title>
    </head>
    <body>
        <div class="main" align="center">
            <h1  align="center">Cinematic</h1>
            <form  method="POST" action="BookingController">
                <input type="hidden" name="command" value="ADDBOOKING"/>
                
                <input type ="image" class="image"name="imgUrl" src="${BOOK.imageUrl}">

                <div style="align-self: center;">
                    <input type="hidden" name="movieId" value="${BOOK.movieId}">
                    <input type="text" class="info" name="movieName" value="${BOOK.movieName}"  readonly>
                    <br> 
                    <lable style="color: gray;">Screening theater:</lable><br>
                    <input type="text" class="info" name="theaterName" value="${BOOK.theaterName}"  readonly>
                    <br>
                    <select name ="bookDate" id="datemenu1" class="info">
                        <option>Select a Screening Date</option>
                    </select>

                    <br>
                    <select  class="info" name="bookTime" required>
                        <option value="" disabled selected style="color:gray">Select Screening time</option>
                        <option value=9:00AM style="color: black">9:00AM</option>
                        <option value=1:00PM style="color: black">1:00PM</option>
                        <option value=5:00PM style="color: black">5:00PM</option>
                        <option value=9:00PM style="color: black">9:00PM</option>
                    </select>       
                    <br>
                    <div>
                        <lable>per ticket 1500LKR</lable>
                        <input type="text" class="info"  name="Adult" placeholder="Enter Adult Ticket Amount" required>
                    </div>
                    <div>
                        <lable>per ticket 850LKR</lable>
                        <input type="text" class="info" name="Child" value="0" placeholder="Enter Child Ticket Amount">
                    </div>
                    <br>
                    <select style="float: right;margin-right:40px;" class="info" name="payMethod" required>
                        <option value="" disabled selected style="color:gray">Select Payment method</option>
                        <option value=creditCard style="color: black">Credit Card</option>
                        <option value=debitCard style="color: black">Debit Card</option>
                    </select>
                    <br> 
                    <br>



                    <input  style="left: 50%" type="submit" class="button" value="Procced to Pay" >

                    <br>

                </div>
            </form>
            <c:url var="home" value="UserHomeController">
            </c:url>
            <a  href="${home}" style="margin-left: 100px;">Home</a>

        </div>
    </body>
    <script>
        function formatDate(date) {
            var d = new Date(date),
                    month = '' + (d.getMonth() + 1),
                    day = '' + d.getDate(),
                    year = d.getFullYear();

            if (month.length < 2)
                month = '0' + month;
            if (day.length < 2)
                day = '0' + day;

            return [year, month, day].join('-');
        }
        var curr = new Date;
        var first = curr.getDate()
        var firstday = (new Date(curr.setDate(first))).toString();
        var options = "";
        for (var i = 0; i < 7; i++) {
            var next = new Date(curr.getTime());
            next.setDate(first + i);
            options += '<option style="color:black;">' + formatDate((next.toString())) + '</option>';
        }
        $("#datemenu1").append(options);


    </script>

</html>
