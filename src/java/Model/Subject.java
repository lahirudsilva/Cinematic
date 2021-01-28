/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Lahiru De silva
 */
public interface Subject {
    
    public void registerObserver(Observer observer);
    
    public void removeObserver(Observer observer);
    
    public void notifyObservers();
}
