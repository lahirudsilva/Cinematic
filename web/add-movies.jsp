<%-- 
    Document   : add-movies
    Created on : Jan 18, 2021, 2:19:02 AM
    Author     : Lahiru De silva 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="CSS/MovieForm.css">
        <link rel="icon" type="image/png" href="images/cinematicLogo.png">
        <title>Cinematic - Buy Movie Tickets Online for the Latest Movies</title>
    </head>
    <body>
        <div class="main" align="center">
            <h1  align="center">Cinematic</h1>
            <p class="su">New Movie Form</p>
            <form class="form1" align="center" method="POST" action="AddMovieController" enctype="multipart/form-data">
                <input type="hidden" name="command" value="AddMovies"/>
                <input name="MovieName" class="first" type="text" placeholder="Movie Name" required autofocus>
                <input name="Theater" class="first" type="text" placeholder="Theater name" required autofocus>
                
                <select  name="Category" required>
                    <option value="" disabled selected style="color:gray">Select Category</option>
                    <option value="Horror">Horror</option>
                    <option value="Comedy">Comedy</option>
                    <option value="Action">Action</option>
                    <option value="Thriller">Thriller</option>
                    <option value="Animation">Animation</option>  
                    <option value="Documentary">Documentary</option>
                    <option value="adventure ">adventure </option>
                </select>
                
                <select name="status" required>
                    <option value="" disabled selected style="color:gray">Select Screening Status</option>
                    <option value="NowShowing">Now showing</option>
                    <option value="ComingSoon">Coming Soon</option>
                </select>
               
                <div class="form-row">  
                    <div class="value">
                        <div class="input-group js-input-file">
                            <input class="input-file" type="file" name="fileToUpload" id="file" required>
                            <label class="label--file" for="file">Choose file</label>
                            <span class="input-file__info">No file chosen</span>
                        </div>
                        <div class="label--desc">Upload the movie cover. Max file size 10 MB</div>
                    </div>
                    
                </div>
                <div class="preview"> <img src="MovieImages/preview.png" id="previewPhoto" size="50"></div>



                <input class="submit" type="submit" value="Add movie" align="center">

                
            </form>
            <c:url var="home" value="AdminHomeController">
                <c:param name="command" value="HOME"></c:param>
            </c:url>
            <div class="home">
            <a  href="${home}" style="margin-left: 150px;">Home</a>
            </div>
        </div>

    </body>
    <script src="js/app.js"></script>
    
</html>
