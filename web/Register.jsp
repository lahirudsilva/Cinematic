<%-- 
    Document   : Register
    Created on : Jan 10, 2021, 2:09:29 AM
    Author     : Lahiru De silva 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Cherry+Swash" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="CSS/Register.css">
        <link rel="icon" type="image/png" href="images/cinematicLogo.png">
        <title>Cinematic - Sign Up</title>
    </head>

    <body>
        <div class="main" align="center">
            <h1  align="center">Cinematic</h1>
            <p class="su">Sign up</p>
            <form class="form1" align="center" method="POST" action="RegisterController"
                  oninput='confirmPassword.setCustomValidity(confirmPassword.value != password.value ? "Passwords do not match." : ""); 
                  phNumber.setCustomValidity(!phNumber.value.match(/^\d{10}$/) ? "Please input valid contact number." : "")'>
                <input name="fName" class="first" type="text" placeholder="First Name" required autofocus>
                <input name="lName" class="first" type="text" placeholder="Last Name" required autofocus>
                <input name="email" class="mail" type="email" placeholder="Email Address" required>
                <input name="password" class="pass1" type="password" placeholder="Choose Password" required>
                <input name="confirmPassword" class="pass2" type="password" placeholder="Retype Password" required>
                <input name="dob" placeholder="Birthday" class="cal" type="text" onfocus="(this.type = 'date')" id="date" required>
                <input name="phNumber" class="Phno" type="phone" placeholder="Phone number" required>


                <input class="submit" type="submit" value="Sign up" align="center">

                <p class="login">  Already have an account? <a href="Login.jsp" style="color: #ff4350;">Login!</a> </p>
            </form>
        </div>


    </body>

</html>
