/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.WorldFill;

/**
 *
 * @author Krongrah
 */
public class Clue {

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

    //getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isBadge() {
        if (name == "bloodSplatteredBadgeClue") {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the isConvictable
     */
    public boolean isConvictable() {
        return isConvictable;
    }
}
