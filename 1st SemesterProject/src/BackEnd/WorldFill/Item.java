/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.WorldFill;

import java.io.Serializable;

public class Item implements Serializable {

    // creating the shared attributes
    private String name;
    private String description;
    private Boolean collectible;
    private Boolean isClue;
    private Clue clue;

    /**
     * Constructor for Items
     * @param newName The name
     * @param newDescription The description, what is it, and how does it look. 
     * @param newIsClue Is the item a clue? true/false.
     * @param newCollectible Can you pick it up? true/false
     * @param newClue The clue to release if item is a clue.
     */
    //creating the constructor for the purpose of making objects of the item class
    public Item(String newName, String newDescription, boolean newIsClue, boolean newCollectible, Clue newClue) {
        name = newName;
        description = newDescription;
        isClue = newIsClue;
        collectible = newCollectible;
        clue = newClue;
    }
    
    /**
     * Can you pick up the item?
     * @return collectible
     */
    public boolean getCollectible() {
        return collectible;
    }
    
    /**
     * Is the item a clue
     * @return isClue
     */
    public boolean getIsClue() {
        return isClue;
    }
    
    /**
     * Sets items clue state to false
     */
    public void deactivateClue() {
        isClue = false;
    }
    
    /**
     *Gets the name of the item. 
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the description
     * @return description 
     */
    public String getDescription() {
        return description;
    }
    
/**
 * Show what the clue of the item is.
 * @return clue
 */
    public Clue giveClue() {
        return clue;
    }
}
