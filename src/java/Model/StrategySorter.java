/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author Lahiru De silva
 */
public class StrategySorter {
    StrategySort sortObj;
    
    public StrategySort getSortObj(){
        return sortObj;
    }
    
    public void setSortObject(StrategySort sortObj) {
        this.sortObj = sortObj;
    }
    
    public List<Reservation> sortScreeningDate(){
        return sortObj.SortScreeningDate();
    }
}
