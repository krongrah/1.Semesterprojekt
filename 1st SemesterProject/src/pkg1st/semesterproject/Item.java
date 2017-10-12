/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1st.semesterproject;

public class Item {
    String name;
    String description;
    Boolean collectible;
    Boolean isClue;
    
    public boolean getCollectible() {
        return collectible;
        
    }
    public boolean getIsclue() {
        return isClue;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Item(String newName, String newDescription, boolean newIsClue, boolean newCollectible) {
        newName = name;
        newDescription = description;
        newIsClue = isClue;
        newCollectible = collectible;
        
    }

    
}
