/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1st.semesterproject;

/**
 *
 * @author Krongrah
 */
public class Clue {
    
    //Attributes
    private String name;
    private String description;
    
    //constructor
    Clue(String _name, String _description){
    name=_name;
    description=_description;
    }
    
    //getters
    public String getName(){
    return name;
    }
    
    public String getDescription(){
    return description;
    }
    
    
    
    
}
