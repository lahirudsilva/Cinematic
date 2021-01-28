/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author Lahiru De silva
 */
public class BookAdapter implements BookAdapterInterface{

    @Override
    public String getDate() {
        LocalDate date = LocalDate.now();
        return date.toString();
    }
    
}
