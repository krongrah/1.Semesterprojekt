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

    //constructor
    public Clue(String _name, String _description, boolean _isConvictable) {
        name = _name;
        description = _description;
        isConvictable = _isConvictable;
    }

    /**
     * Gets clue name
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets clue description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the isConvictable
     */
    public boolean isConvictable() {
        return isConvictable;
    }
}
