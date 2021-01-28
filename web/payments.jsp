<%-- 
    Document   : payments
    Created on : Jan 24, 2021, 9:04:16 PM
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
        <link rel="stylesheet" href="CSS/payments.css">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="icon" type="image/png" href="images/cinematicLogo.png">
        <title>Cinematic - Buy Movie Tickets Online for the Latest Movies</title>
    </head>
    <body>
        <div class="main" align="center">
            <h1 align="center">Cinematic</h1>
            <h3>Payment Information</h3>
            <div class="form-container">
                <lable>Total Price(LKR):</lable><br>
                <input type="text" class="info" value="${ticket.totalPrice}">
                <div class="field-container">
                    <label for="name">Name</label>
                    <input id="name" maxlength="20" type="text">
                </div>
                <div class="field-container">
                    <label for="cardnumber">Card Number</label>
                    <input id="cardnumber" type="text" pattern="[0-9]*" inputmode="numeric">
                    <svg id="ccicon" class="ccicon" width="750" height="471" viewBox="0 0 750 471" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">

                    </svg>
                </div>
                <div class="field-container">
                    <label for="expirationdate">Expiration (mm/yy)</label>
                    <input id="expirationdate" type="text" pattern="[0-9]*" inputmode="numeric">
                </div>
                <div class="field-container">
                    <label for="securitycode">Security Code</label>
                    <input id="securitycode" type="text" pattern="[0-9]*" inputmode="numeric">
                </div>
                
                <c:url var="confirm" value="BookingController">
                    <c:param name="command" value="MYBOOKINGS"/>
                </c:url>

                <a onclick="function" href="${confirm}">Confirm payment</a>
            </div>
        </div>
    </body>
    <script>
        
            function () {
                swal({
                    title: "Good job!",
                    text: "You clicked the button!",
                    icon: "success",
                    button: "Aww yiss!",
                });
            }

      
    </script>

</html>
