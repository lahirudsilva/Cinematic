/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lahiru De silva
 */
public class Movies  {

    private int movieId;
    private String movieName, theaterName, category, imageUrl, screeningStatus;

    

    public Movies(String movieName, String theaterName, String category, String imageUrl) {
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Movies(int movieId, String theaterName, String movieName, String category, String screeningDate, String screeningTime, String imgUrl) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.category = category;
        this.imageUrl = imgUrl;
    }

    public Movies(int movieId, String theaterName, String movieName, String category, String imgUrl) {
        this.movieId = movieId;
        this.theaterName = theaterName;
        this.movieName = movieName;
        this.category = category;
        this.imageUrl = imgUrl;
    }

    Movies(int movieId, String theaterName, String movieName, String category, String imgUrl, String ScreeningStatus) {
        this.movieId = movieId;
        this.theaterName = theaterName;
        this.movieName = movieName;
        this.category = category;
        this.imageUrl = imgUrl;
        this.screeningStatus = ScreeningStatus;
    }

    public Movies(String movieName, String theaterName, String category, String photo, String status) {
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.category = category;
        this.imageUrl = photo;
        this.screeningStatus = status;

    }

    Movies(String movieName) {
        this.movieName = movieName;
    }
    
    

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getScreeningStatus() {
        return screeningStatus;
    }

    public void setScreeningStatus(String screeningStatus) {
        this.screeningStatus = screeningStatus;
        

    }



    

}

