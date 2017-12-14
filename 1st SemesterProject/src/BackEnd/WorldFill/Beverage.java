/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.WorldFill;

import java.io.Serializable;

/**
 *
 * @author sebastian
 */
public class Beverage extends Item implements Serializable{
    private int numberOfSips;
    private int alcoholContent;
    
    /**
     * Constructs a Beverage
     * @param newName The name of the Beverage
     * @param newDescription The description of the beverage
     * @param numberOfSips The amount of times this beverage can be drunk
     * @param alcoholContent The amount of alcohol the player gains by drinking this Beverage once.
     */
    public Beverage(String newName, String newDescription, int numberOfSips, int alcoholContent) {
        super(newName, newDescription, false, true, null);
        this.numberOfSips=numberOfSips;
        this.alcoholContent=alcoholContent;
    }
    
    /**
     * 
     * @return Returns the number of sips remaining of the Beverage
     */
    public int getNumberOfSips(){
        return numberOfSips;
    }
    
    /**
     * 
     * @return Returns the alcohol content of the Beverage
     */
    public int getAlcoholContent(){
        return alcoholContent;
    }
    
    /**
     * Decrements the number of sips by one
     */
    public void removeSip(){
        numberOfSips --;
    }
    
}
    

