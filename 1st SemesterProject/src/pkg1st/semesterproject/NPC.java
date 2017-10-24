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
    private Clue clue;
    private int clueCount=0;
    private int clueRelease;
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
    public int getClueCount(){
    clueCount++;
    return clueCount;
    } 
    public int getClueRelease(){
    return clueRelease;
    }
    public Clue giveClue(){
        System.out.println(clue.getName()+" was added to your journal.");
        return clue;
    }
    
    // NPC constructor
    public NPC(String newName, Dialogue dialogue, Clue clue, int clueRelease) {
        this.name = newName;
        this.dialogue=dialogue;
        this.clue=clue;
        this.clueRelease=clueRelease;
    }
}

