/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1st.semesterproject;

/**
 *
 * @author sebastian
 */
public class Beverage extends Item {
    private int numberOfSips;
    private int alcholContent;
    
    public Beverage(String newName, String newDescription, boolean newIsClue, boolean newCollectible, Clue newClue, int numberOfSips, int alcholcontent) {
        super(newName, newDescription, newIsClue, newCollectible, newClue);
    }
    
    public int getNumberOfSips(){
        return numberOfSips;
    }
    
    public int getAlcholContent(){
        return alcholContent;
    }
    
    public void removeSip(){
        numberOfSips -= numberOfSips;
    }
    
}
    

