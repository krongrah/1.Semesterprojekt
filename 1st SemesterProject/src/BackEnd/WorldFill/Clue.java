/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.WorldFill;

import java.io.Serializable;

/**
 *
 * @author Krongrah
 */
public class Clue implements Serializable{

    //Attributes
    private String name;
    private String description;
    private boolean isConvictable;

    /**
     * Constructs a Clue 
     * @param name The name of the Clue
     * @param description The description of the Clue
     * @param isConvictable True if the it can be used to convict, else false.
     */
    public Clue(String name, String description, boolean isConvictable) {
        this.name = name;
        this.description = description;
        this.isConvictable = isConvictable;
    }

    /**
     * Gets clue name
     * @return Returns the name of the clue
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets clue description
     * @return Returns the description of the clue
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return returns true of the Clue can be used to convict, else false.
     */
    public boolean isConvictable() {
        return isConvictable;
    }
}
