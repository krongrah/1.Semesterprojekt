/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.WorldFill;

public class Item {

    // creating the shared attributes
    private String name;
    private String description;
    private Boolean collectible;
    private Boolean isClue;
    private Clue clue;

    //creating the constructor for the purpose of making objects of the item class
    public Item(String newName, String newDescription, boolean newIsClue, boolean newCollectible, Clue newClue) {
        name = newName;
        description = newDescription;
        isClue = newIsClue;
        collectible = newCollectible;
        clue = newClue;
    }
    
    
    
    //creating the getter methods
    public boolean getCollectible() {
        return collectible;

    }

    public boolean getIsClue() {
        return isClue;
    }

    public void deactivateClue() {
        isClue = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Clue giveClue() {
        return clue;
    }

    

}
