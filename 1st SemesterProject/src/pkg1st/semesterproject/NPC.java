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
    static String name;
    static List<String> dialogueList = new ArrayList<String>();
    static int suspectability;
    static int dialogueStep;
    //Moves NPC to another room
    public static void move(String Room) {
        
    }
    public static String getName(){
        return name;
    }
    public static int getSuspectability() {
        return suspectability;
    } 
    public static int getDialogueStep() {
        return dialogueStep;
    }
    public static List<String> getDialogueList() {
        return dialogueList;
    }
    public static void incrementDialogueStep() {
        dialogueStep += 1;
    }
    // NPC constructor
    public NPC(String newName, List<String> newDialogueList) {
        this.name = newName;
        this.dialogueList = newDialogueList;
    }
}

