/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1st.semesterproject;
import java.util.*;

/**
 *
 * @author Krongrah
 */
public class NPC {
    // NPC attributes
    private String name;
    Dialogue dialogue;
    private int suspectability;
    //Moves NPC to another room
    public static void move(String Room) {
        
    }
    public String getName(){
        return name;
    }
    public int getSuspectability() {
        return suspectability;
    } 
    
    public void getDialogue() {
        dialogue.getLine();
        
    }
    public void setDialogue(Dialogue dialogue_){
    dialogue=dialogue_;
    }
    
    // NPC constructor
    public NPC(String newName) {
        this.name = newName;
    }
}

