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
public class NPC {

    // NPC attributes
    private String name;
    private Dialogue dialogue;
    private int suspectability; //todo remove?
    private Clue clue;
    private int askForClueCounter = 0;
    private int clueRelease;

    // NPC constructor
    public NPC(String newName, Dialogue dialogue, Clue clue, int clueRelease) {
        this.name = newName;
        this.dialogue = dialogue;
        this.clue = clue;
        this.clueRelease = clueRelease;
    }
    
    //Moves NPC to another room
    public String getName() {
        return name;
    }

    public int getSuspectability() {
        //todo remove?
        return suspectability;
    }

    public void getLine() {
        System.out.println(name + ": " + dialogue.getLine());
    }

    public boolean getAlibi() {
        return dialogue.getAlibi();
    }

    public void setDialogue(Dialogue dialogue_) { //todo make set this in the constructor instead
        dialogue = dialogue_;
    }

    public int getClueCount() {
        askForClueCounter++;    //todo redo the clue-in-dialogue
        return askForClueCounter;
    }

    public int getClueRelease() {//todo same as above
        return clueRelease;
    }

    public Clue giveClue() {
        System.out.println(clue.getName() + " was added to your journal.");
        return clue;
    }
    
    
    

    
}