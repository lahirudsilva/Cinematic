/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashMap;

/**
 *
 * @author Lahiru De silva
 */
//flyweight
public class MovieFactory {
    
    static HashMap<String, Movies> movieCollection = new HashMap<>();
   
    
    public static Movies getMovie(String movieName) {
        Movies result = movieCollection.get(movieName);
        
        if(result == null)
        {
            result = new Movies(movieName);
            movieCollection.put(movieName, result);
        }
        return result;
    }
    
    
}
