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
    private Clue clue;
    private int askForClueCounter = 0;
    private int clueRelease;
    private String NPCCurrentRoomName;
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

    public void getLine() {
        dialogue.getLine();
    }

    public boolean getAlibi() {
        return getDialogue().getAlibi();
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
        return clue;
    }

    /**
     * @return the dialogue
     */
    public Dialogue getDialogue() {
        return dialogue;
    }

    /**
     * @return the NPCcurrentRoom
     */
    public String getCurrentRoomName() {
        return NPCCurrentRoomName;
    }

    /**
     * @param NPCcurrentRoom the new room of the NPC
     */
    public void setCurrentRoomName(String NPCCurrentRoomName) {
        this.NPCCurrentRoomName = NPCCurrentRoomName;
    }
   
}
