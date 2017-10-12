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
    String[] dialogueList;
    private int suspectability;
    private int dialogueStep;
    //Moves NPC to another room
    public static void move(String Room) {
        
    }
    public String getName(){
        return name;
    }
    public int getSuspectability() {
        return suspectability;
    } 
    public int getDialogueStep() {
        return dialogueStep;
    }
    public String getDialogue() {
        return dialogueList[dialogueStep];
    }
    public void incrementDialogueStep() {
        dialogueStep += 1;
    }
    // NPC constructor
    public NPC(String newName, String[] newDialogueList) {
        this.name = newName;
        this.dialogueList = newDialogueList;
    }
}

