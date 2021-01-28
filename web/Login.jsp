<%-- 
    Document   : Login
    Created on : Jan 10, 2021, 1:10:12 AM
    Author     : Lahiru De silva 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <link rel="stylesheet" href="CSS/login.css">
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Cherry+Swash" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
        <link rel="icon" type="image/png" href="images/cinematicLogo.png">
        <title>Cinematic - Sign In</title>
    </head>

    <body>
        <div class="main">
            <h1  align="center">Cinematic</h1>
            <p class="sign" align="center">Sign in</p>
            <form class="form1" method="POST" action="LoginController">
                <input name="email" class="un " type="text" align="center" placeholder="Email">
                <input name="password" class="pass" type="password" align="center" placeholder="Password">
                <button class="submit" align="center">Sign in</button>
                <p class="regi" align="center"> Don't have an account? <a href="Register.jsp" style="color: #ff4350;">Register!</a>  </p>
            </form>

        </div>

    </body>

</html>
