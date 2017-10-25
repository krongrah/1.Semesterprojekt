/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1st.semesterproject;

public class Item {

    // creating the shared attributes
    String name;
    String description;
    Boolean collectible;
    Boolean isClue;
    Clue clue;

    //creating the getter methods
    public boolean getCollectible() {
        return collectible;

    }

    public boolean getIsclue() {
        return isClue;
    }
    public void setIsClue(){
    isClue=false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public Clue giveClue(){
    return clue;
    }

    //creating the constructor for the purpose of making objects of the item class
    public Item(String newName, String newDescription, boolean newIsClue, boolean newCollectible, Clue newClue) {
        name = newName;
        description = newDescription;
        isClue = newIsClue;
        collectible = newCollectible;
        clue =newClue;
    }

}
