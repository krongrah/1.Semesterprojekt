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
    Dialogue alibi;
    private int suspectability;
    private Clue clue;
    private int askForClueCounter = 0;
    private int clueRelease;

    //Moves NPC to another room


    public String getName() {
        return name;
    }

    public int getSuspectability() {
        return suspectability;
    }

    public void getDialogue() {
        dialogue.getLine();
    }
    
    public void getAlibi(){    
        dialogue.getAlibi();
    }

    public void setDialogue(Dialogue dialogue_) {
        dialogue = dialogue_;
    }

    public int getClueCount() {
        askForClueCounter++;
        return askForClueCounter;
    }

    public int getClueRelease() {
        return clueRelease;
    }

    public Clue giveClue() {
        System.out.println(clue.getName() + " was added to your journal.");
        return clue;
    }

    // NPC constructor
    public NPC(String newName, Dialogue dialogue, Dialogue alibi, Clue clue, int clueRelease) {
        this.name = newName;
        this.dialogue = dialogue;
        this.alibi= alibi;
        this.clue = clue;
        this.clueRelease = clueRelease;
    }
}
